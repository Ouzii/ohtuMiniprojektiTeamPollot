package tip.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tip.domain.Tip;
import tip.repository.DetailRepository;
import tip.repository.TagRepository;
import tip.repository.TipRepository;
import tip.service.validators.BookValidator;

@Controller
@Transactional
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
    public String addBook(
            @RequestParam String name,
            @RequestParam String writer,
            @RequestParam String isbn,
            @RequestParam String date,
            @RequestParam String description,
            RedirectAttributes attributes) {

        if (writer == null || writer.trim().isEmpty()) {
            writer = "tuntematon";
        }

        Tip tip = new Tip(name, "kirja");
        List<String> errors = new ArrayList<>();
        errors.addAll(tipNameIsUnique(tip));
        tip.setRead(false);

        super.makeDetail(isbn.trim(), "isbn", tip);
        super.makeDetail(writer, "kirjoittaja", tip);
        super.makeDetail(date, "julkaisupvm", tip);
        super.makeDetail(description, "kuvaus", tip);

        errors.addAll(bookValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/kirja/{tipId}")
    public String editBook(
            Model model,
            @PathVariable Long tipId,
            @RequestParam String writer,
            @RequestParam String description,
            @RequestParam int read,
            @RequestParam String name,
            @RequestParam String isbn,
            @RequestParam String date,
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        List<String> errors = new ArrayList<>();
        setTipRead(tip, read);
        errors.addAll(tipNameIsUnique(tip));
        errors.addAll(handleDetail(writer, "kirjoittaja", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(isbn, "isbn", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(date, "julkaisupvm", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(description, "kuvaus", tip, bookValidator.getNotNullDetailKeys()));
        errors.addAll(bookValidator.validate(tip));
        if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/kirja/" + tipId;

    }

}
