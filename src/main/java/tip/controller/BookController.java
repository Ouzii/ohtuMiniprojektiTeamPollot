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
import tip.domain.Tip;
import tip.service.validators.BookValidator;

@Controller
public class BookController extends SuperController {

    @Autowired
    BookValidator bookValidator;

    @GetMapping("/book")
    public String addForm(Model model) {
        return "addBook";
    }

    @PostMapping("/newBook")
    public String addBook(
            @RequestParam String name,
            @RequestParam String writer,
            @RequestParam String isbn,
            @RequestParam String date,
            @RequestParam String comment,
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
        super.makeDetail(comment, "kommentti", tip);

        errors.addAll(bookValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/book/{tipId}")
    public String editBook(Model model,
            @PathVariable Long tipId,
            @RequestParam String writer,
            @RequestParam int read,
            @RequestParam String name,
            @RequestParam String isbn,
            @RequestParam String date,
            @RequestParam String comment,
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        List<String> errors = new ArrayList<>();
        setTipRead(tip, read);
        errors.addAll(handleDetail(writer, "writer", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(isbn, "isbn", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(date, "date", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(comment, "kommentti", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(bookValidator.validate(tip));
        if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/book/" + tipId;

    }

}
