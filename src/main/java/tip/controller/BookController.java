package tip.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tip.domain.Book;
import tip.domain.Tag;
import tip.repository.BookRepository;
import tip.repository.TagRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("tips", bookRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String addBook(@RequestParam String name, @RequestParam String writer, @RequestParam String isbn) {
        Book book = new Book(name, writer, isbn.trim());
        List<String> errors = book.validate(); // ei hirveesti kiinnosta 
        // selvittää miten tämä 
        // tomcat tuon errorsin haluaa
        // siirrettävän viewiin. 
        if (errors.size() == 0) {
            this.bookRepository.save(book);
        }
        return "redirect:/";
    }

    @PostMapping("/{tipId}/addTag/{tagId}")
    public String addTagToBook(@PathVariable Long tipId, @PathVariable Long tagId) {
        Book book = bookRepository.getOne(tipId);
        Tag tag = tagRepository.getOne(tagId);

        book.addTag(tag);
        tag.addBook(book);

        return "redirect:/";
    }

    @DeleteMapping("/{tipId}")
    public String delete(@PathVariable Long tipId) {
        bookRepository.delete(bookRepository.getOne(tipId));
        return "redirect:/";
    }

    @GetMapping("/{tipId}")
    public String list(Model model, @PathVariable Long tipId) {
        model.addAttribute("tip", bookRepository.findOne(tipId));
        model.addAttribute("tags", tagRepository.findAll());
        return "modifyBook";
    }

    @PostMapping("/{tipId}")
    public String mode(Model model, @PathVariable Long tipId,
            @RequestParam String name, @RequestParam String isbn, RedirectAttributes attributes) {
        Book book = bookRepository.findOne(tipId);
        book.setIsbn(isbn.trim());
        book.setName(name);

        List<String> errors = book.validate();
        if (errors.isEmpty()) {
            bookRepository.save(book);
            attributes.addFlashAttribute("message", "tip has succesfully been modified olalala");
            return "redirect:/";
        }
        attributes.addFlashAttribute("errors", errors);
        return "redirect:/" + tipId;

    }
}
