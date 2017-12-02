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
    
    @PostMapping("/{tipId}/addTag")
    public String addTagToTip(@PathVariable Long tipId, @RequestParam Long tagId) {
        Tip book = tipRepository.getOne(tipId);
        Tag tag = tagRepository.getOne(tagId);

        book.addTag(tag);
        tag.addTip(book);

        tipRepository.save(book);
        tagRepository.save(tag);

        return "redirect:/" + tipId;
    }
    
    @DeleteMapping("/{tipId}/deleteTag")
    public String deleteFromTip(@PathVariable Long tipId, @RequestParam Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        Tip tip = tipRepository.getOne(tipId);
        tip.getTags().remove(tag);
        tipRepository.save(tip);
        
        return "redirect:/" + tipId;
    }
    
}
