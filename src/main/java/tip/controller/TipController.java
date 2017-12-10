package tip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tip.domain.Tag;
import tip.domain.Tip;
import tip.repository.TagRepository;
import tip.repository.TipRepository;

@Controller
public class TipController {
	
	
	private static final Map<String, String> tipTypeMap;
    static {
        tipTypeMap = new HashMap<String, String>();
        tipTypeMap.put("book", "modifyBook");
        tipTypeMap.put("podcast", "modifyPodcast");
		tipTypeMap.put("video", "modifyVideo");
		tipTypeMap.put("blogpost", "modifyBlogpost");	
    }
	

    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("tips", tipRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
        return "index";
    }

    @Transactional
    @DeleteMapping("/{tipId}")
    public String delete(@PathVariable Long tipId) {
        Tip book = tipRepository.getOne(tipId);
        tipRepository.delete(book);
        return "redirect:/";
    }

    @GetMapping("{tipType}/{tipId}")
    public String show(Model model, @PathVariable Long tipId, @PathVariable String tipType) {
        Tip tip = tipRepository.findOne(tipId);
        List<Tag> nonHavingTags = tagRepository.findAll();
        nonHavingTags.removeAll(tip.getTags());

        model.addAttribute("tip", tip);
        model.addAttribute("tags", nonHavingTags);
		
		if(tipTypeMap.containsKey(tipType))
			return tipTypeMap.get(tipType);
		else
			return "redirect:/";

    }
}
