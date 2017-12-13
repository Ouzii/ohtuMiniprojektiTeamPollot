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
import tip.repository.DetailRepository;
import tip.repository.TagRepository;
import tip.repository.TipRepository;
import tip.service.validators.BlogpostValidator;

@Controller
public class BlogpostController extends SuperController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    BlogpostValidator blogpostValidator;

    @GetMapping("/blogpost")
    public String addForm(Model model) {
        return "addBlogpost";
    }

    @PostMapping("/newBlogpost")
    public String addBlogpost(
            @RequestParam String name,
            @RequestParam String blogName,
            @RequestParam String writer,
            @RequestParam String url, 
            @RequestParam String description,
            @RequestParam String date,
            RedirectAttributes attributes) {
        
        if (writer == null || writer.trim().isEmpty()) {
            writer = "tuntematon";
        }
        Tip tip = new Tip(name, "blogpost");
        tip.setRead(false);

        List<String> errors = new ArrayList<>();
        errors.addAll(tipNameIsUnique(tip));
        super.makeDetail(url, "url", tip);
        super.makeDetail(blogName, "blogin nimi", tip);
        super.makeDetail(writer, "kirjoittaja", tip);
        super.makeDetail(date, "julkaisupvm", tip);
        super.makeDetail(description, "kuvaus", tip);

        errors.addAll(blogpostValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/blogpost/{tipId}")
    public String editBlogpost(
            Model model, 
            @PathVariable Long tipId,
            @RequestParam String writer,
            @RequestParam int read,
            @RequestParam String name, 
            @RequestParam String blogName, 
            @RequestParam String description,
            @RequestParam String url,
            @RequestParam String date,
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        List<String> errors = new ArrayList<>();
        setTipRead(tip, read);

        errors.addAll(tipNameIsUnique(tip));
        errors.addAll(handleDetail(url, "url", tip, blogpostValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(writer, "kirjoittaja", tip, blogpostValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(date, "julkaisupvm", tip, blogpostValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(description, "kuvaus", tip, blogpostValidator.getNotNullDetailKeys()));
        errors.addAll(blogpostValidator.validate(tip));
        if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/blogpost/" + tipId;

    }

}
