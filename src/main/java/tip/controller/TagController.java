package tip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tip.domain.Book;
import tip.domain.Tag;
import tip.repository.BookRepository;
import tip.repository.TagRepository;

@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/newTag")
    public String addTag(@RequestParam String tag_name) {
        if (tag_name.trim() != null && !tag_name.trim().isEmpty()) {
            Tag tag = new Tag(tag_name);
            this.tagRepository.save(tag);
        }
        return "redirect:/";
    }
    
    
    @DeleteMapping("/deleteTag/{tagId}")
    public String delete(@PathVariable Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        for (Book book : tag.getBooks()) {
            book.removeTag(tag);
        }
        tag.getBooks().clear();
        tagRepository.save(tag);
        tagRepository.delete(tag);
        return "redirect:/";
    }
    
    @DeleteMapping("/{tipId}/deleteTag")
    public String deleteFromBook(@PathVariable Long tipId, @RequestParam Long tagId) {
        Tag tag = tagRepository.getOne(tagId);
        Book book = bookRepository.getOne(tipId);
        book.getTags().remove(tag);
        bookRepository.save(book);
        
        return "redirect:/" + tipId;
    }
    
}
