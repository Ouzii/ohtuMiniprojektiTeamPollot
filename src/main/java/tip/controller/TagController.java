package tip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tip.domain.Tag;
import tip.repository.TagRepository;

@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @PostMapping("/newTag")
    public String addTag(@RequestParam String name) {
        if (name.trim() != null && !name.trim().isEmpty()) {
            Tag tag = new Tag(name);
            this.tagRepository.save(tag);
        }
        return "redirect:/";
    }
    
    
    @DeleteMapping("/{tagId}")
    public String delete(@PathVariable Long tagId) {
        tagRepository.delete(tagRepository.getOne(tagId));
        return "redirect:/";
    }
    
}
