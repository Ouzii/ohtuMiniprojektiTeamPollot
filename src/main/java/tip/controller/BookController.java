package tip.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tip.domain.Detail;
import tip.domain.Tip;
import tip.repository.DetailRepository;
import tip.repository.TagRepository;
import tip.repository.TipRepository;
import tip.service.validators.BookValidator;

@Controller
public class BookController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    BookValidator bookValidator;


    @GetMapping("/book")
    public String addForm(Model model) {
        return "addBook";
    }

    @PostMapping("/newBook")
    public String addBook(@RequestParam String name, @RequestParam String writer, @RequestParam String isbn, 
            @RequestParam String date,
            RedirectAttributes attributes) {
        if(writer == null || writer.trim().isEmpty()) {
            writer = "tuntematon";
        }
        List<String> errors = new ArrayList<>();
        if (tipRepository.findByName(name) != null) {
            errors.add(("Saman niminen karjainmerkki on jo olemassa!"));
        }
        Tip tip = new Tip(name, "book");
        Detail i = new Detail(isbn.trim());
        Detail w = new Detail(writer);
        Detail dateDetail = new Detail(date);
        tip.setRead(false);
    //    Detail readDetail = new Detail("0");

  //      tip.addDetail("read", readDetail);
        tip.addDetail("isbn", i);
        tip.addDetail("writer", w);
        tip.addDetail("date",dateDetail);

        errors.addAll(bookValidator.validate(tip));
        if (errors.isEmpty()) {
            detailRepository.save(i);
            detailRepository.save(w);
            detailRepository.save(dateDetail);
            this.tipRepository.save(tip);
        } else {
            attributes.addFlashAttribute("errors", errors);
        }

        return "redirect:/";
    }
    
    @PostMapping("/book/{tipId}")
    public String editBook(Model model, @PathVariable Long tipId, @RequestParam String writer,
            @RequestParam int read, @RequestParam String name, @RequestParam String isbn,  @RequestParam String date, RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);
       
        if(read == 1) {
            tip.setRead(true);
        } else {
            tip.setRead(false);
        }
        
        Detail isbnDetail = tip.getDetails().get("isbn");
        isbnDetail.setValue(isbn.trim());

        if(writer == null || writer.trim().isEmpty()) {
            writer = "tuntematon";
        }
        Detail writerDetail = tip.getDetails().get("writer");
        writerDetail.setValue(writer.trim());
        

        List<String> errors = bookValidator.validate(tip);
        if (errors.isEmpty()) {
            tipRepository.save(tip);
            attributes.addFlashAttribute("message", "tip has succesfully been modified olalala");
            return "redirect:/";
        }
        attributes.addFlashAttribute("errors", errors);
        return "redirect:/book/" + tipId;

    }
    
}
