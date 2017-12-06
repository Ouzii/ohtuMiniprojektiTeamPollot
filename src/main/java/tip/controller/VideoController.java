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
    public String addVideo(@RequestParam String name, @RequestParam String artist, @RequestParam String url,
            @RequestParam String date, RedirectAttributes attributes) {
        if (artist == null || artist.trim().isEmpty()) {
            artist = "tuntematon";
        }
        List<String> errors = super.tipNameIsUnique(name);

        Tip tip = new Tip(name, "video");
        tip.setRead(false);

        super.makeDetail(url, "url", tip);
        super.makeDetail(artist, "artist", tip);
        super.makeDetail(date, "date", tip);    

        errors.addAll(videoValidator.validate(tip));
        super.saveTip(errors, tip, attributes, DEFAUL_ADD_SUCC_MSG);

        return "redirect:/";
    }

    @PostMapping("/video/{tipId}")
    public String editVideo(Model model, @PathVariable Long tipId, @RequestParam String artist,
            @RequestParam int read, @RequestParam String name, @RequestParam String url, @RequestParam String date, RedirectAttributes attributes) {

        Tip tip = tipRepository.findOne(tipId);
        tip.setName(name);
        super.setTipRead(tip, read);

        makeDetail(url, "url", tip);
        makeDetail(artist, "artist", tip);
        makeDetail(date, "date", tip);

        List<String> errors = videoValidator.validate(tip);
       if (saveTip(errors, tip, attributes, DEFAUL_MODE_SUCC_MSG)) {
            return "redirect:/";
        }
        return "redirect:/video/" + tipId;

    }

}
