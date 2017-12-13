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
import tip.service.validators.VideoValidator;

@Controller
public class VideoController extends SuperController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    VideoValidator videoValidator;

    @GetMapping("/video")
    public String addForm(Model model) {
        return "addVideo";
    }

    @PostMapping("/newVideo")
    public String addVideo(
            @RequestParam String name,
            @RequestParam String publisher,
            @RequestParam String url,
            @RequestParam String date,
            @RequestParam String description,
            RedirectAttributes attributes) {

        if (publisher == null || publisher.trim().isEmpty()) {
            publisher = "tuntematon";
        }

        Tip tip = new Tip(name, "video");
        List<String> errors = new ArrayList<>();
        errors.addAll(tipNameIsUnique(tip));
        tip.setRead(false);

        super.makeDetail(url, "url", tip);
        super.makeDetail(publisher, "tekijä", tip);
        super.makeDetail(date, "julkaisupvm", tip);
        makeDetail(description, "kuvaus", tip);
        errors.addAll(videoValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/video/{tipId}")
    public String editVideo(Model model,
            @PathVariable Long tipId,
            @RequestParam String publisher,
            @RequestParam int read,
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam String date,
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        List<String> errors = new ArrayList<>();
        setTipRead(tip, read);
        errors.addAll(tipNameIsUnique(tip));
        errors.addAll(handleDetail(url, "url", tip, videoValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(publisher, "tekijä", tip, videoValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(date, "julkaisupvm", tip, videoValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(description, "kuvaus", tip, videoValidator.getNotNullDetailKeys()));
        errors.addAll(videoValidator.validate(tip));

        if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/video/" + tipId;

    }

}
