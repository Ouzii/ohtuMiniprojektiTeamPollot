package tip.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tip.domain.Book;
import tip.repository.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("tips", bookRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String add(@RequestParam String name, @RequestParam String isbn) {
        Book book = new Book(name, isbn);
        this.bookRepository.save(book);
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
        return "modifyBook";
    }
    @PostMapping("/{tipId}")
    public String mode(Model model, @PathVariable Long tipId
    , @RequestParam String name, @RequestParam String isbn) {
        Book book = bookRepository.findOne(tipId);
        ArrayList errors = new ArrayList();
        book.setIsbn(isbn);
        book.setName(name);
        if (book.validateISBN()) {
            bookRepository.save(book);
            return "redirect:/";
        }
        errors.add("ISBN is in incorrect format!");
        model.addAttribute(errors);
        return "redirect:/" + tipId;
    }
}

