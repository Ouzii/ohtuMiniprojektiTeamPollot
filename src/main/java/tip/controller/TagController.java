package tip.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tip.domain.Tip;
import tip.domain.Tag;
import tip.repository.TagRepository;
import tip.repository.TipRepository;
import tip.service.validators.TagValidator;

@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TipRepository tipRepository;
    @Autowired
    TagValidator tagValidator;

    @GetMapping("/tags")
    public String listTags(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "tags";
    }

    @PostMapping("/newTag")
    public String addTag(@RequestParam String tag_name) {
        Tag tag = new Tag(tag_name);
        List<String> errors = tagValidator.validateTag(tag);
        if (tagRepository.findByName(tag_name) != null) {
            errors.add("Samanniminen tägi on jo olemassa!");
        }
        if (errors.size() == 0) {
            this.tagRepository.save(tag);
        }
        return "redirect:/tags";
    }

    @DeleteMapping("/deleteTag/{tagId}")
    public String delete(@PathVariable Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        for (Tip book : tag.getTips()) {
            book.removeTag(tag);
        }
        tag.getTips().clear();
        tagRepository.save(tag);
        tagRepository.delete(tag);
        return "redirect:/tags";
    }

    @DeleteMapping("/{tipId}/deleteTag")
    public String deleteFromTip(@PathVariable Long tipId, @RequestParam(value = "tagId", required = false) Long tagId) {
        Tip tip = tipRepository.getOne(tipId);
        if (tagId == null) {
            return "redirect:/" + tip.getType() + "/" + tip.getId();
        }
        Tag tag = tagRepository.getOne(tagId);
        
        tip.getTags().remove(tag);
        tipRepository.save(tip);

        return "redirect:/" + tip.getType() + "/" + tip.getId();
    }

    @PostMapping("/{tipId}/addTag")
    public String addTagToTip(@PathVariable Long tipId, @RequestParam(value = "tagId", required = false) Long tagId) {
        Tip tip = tipRepository.getOne(tipId);
        if (tagId == null) {
            return "redirect:/" + tip.getType() + "/" + tip.getId();
        }
        Tag tag = tagRepository.getOne(tagId);

        tip.addTag(tag);
        tag.addTip(tip);

        tipRepository.save(tip);
        tagRepository.save(tag);

        return "redirect:/" + tip.getType() + "/" + tip.getId();
    }

}
