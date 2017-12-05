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
public class BookController extends SuperController {

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
        if (writer == null || writer.trim().isEmpty()) {
            writer = "tuntematon";
        }
        List<String> errors = super.tipNameIsUnique(name);

        Tip tip = new Tip(name, "book");
        tip.setRead(false);

        super.makeDetail(isbn.trim(), "isbn", tip);
        super.makeDetail(writer, "writer", tip);
        super.makeDetail(date, "date", tip);

        errors.addAll(bookValidator.validate(tip));
        super.saveTip(errors, tip, attributes);

        return "redirect:/";
    }

    @PostMapping("/book/{tipId}")
    public String editBook(Model model, @PathVariable Long tipId, @RequestParam String writer,
            @RequestParam int read, @RequestParam String name, @RequestParam String isbn, @RequestParam String date, RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);
        super.setTipRead(tip, read);
        super.makeDetail(writer, "writer", tip);
        super.makeDetail(date, "date", tip);
        super.makeDetail(isbn, "isbn", tip);

        List<String> errors = bookValidator.validate(tip);
        if (saveTip(errors, tip, attributes)) {
            return "redirect:/";
        }
        return "redirect:/book/" + tipId;

    }

}
