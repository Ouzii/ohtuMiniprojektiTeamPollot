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
import tip.service.validators.PodcastValidator;

@Controller
@Transactional
public class PodcastController extends SuperController {

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    PodcastValidator podcastValidator;

    @GetMapping("/podcast")
    public String addForm(Model model) {
        return "addPodcast";
    }

    @PostMapping("/newPodcast")
    public String addPodcast(
            @RequestParam String name,
            @RequestParam String publisher,
            @RequestParam String podcastName,
            @RequestParam String url,
            @RequestParam String description,
            @RequestParam String date,
            RedirectAttributes attributes) {
        
        if (publisher == null || publisher.trim().isEmpty()) {
            publisher = "tuntematon";
        }

        Tip tip = new Tip(name, "podcast");
        tip.setRead(false);
        List<String> errors = new ArrayList<>();
        errors.addAll(tipNameIsUnique(tip));
        super.makeDetail(url, "url", tip);
        super.makeDetail(publisher, "tekijä", tip);
        super.makeDetail(podcastName, "podcastin nimi", tip);
        super.makeDetail(date, "julkaisupvm", tip);
        makeDetail(description, "kuvaus", tip);
        errors.addAll(podcastValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/podcast/{tipId}")
    public String editPodcast(
            Model model,
            @PathVariable Long tipId,
            @RequestParam String publisher,
            @RequestParam String podcastName,
            @RequestParam String name, 
            @RequestParam String url, 
            @RequestParam String date, 
            @RequestParam String description,
            @RequestParam int read, 
            RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);

        List<String> errors = new ArrayList<>();
        setTipRead(tip, read);
        errors.addAll(tipNameIsUnique(tip));
        errors.addAll(handleDetail(url, "url", tip, podcastValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(publisher, "tekijä", tip, podcastValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(podcastName, "podcastin nimi", tip, podcastValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(date, "julkaisupvm", tip, podcastValidator.getNotNullDetailKeys()));
        errors.addAll(handleDetail(description, "kuvaus", tip, podcastValidator.getNotNullDetailKeys()));
        errors.addAll(podcastValidator.validate(tip));
        if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/podcast/" + tipId;

    }

}
