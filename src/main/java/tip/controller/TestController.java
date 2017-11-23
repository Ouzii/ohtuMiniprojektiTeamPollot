package tip.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tip.domain.Book;
import tip.domain.Tag;
import tip.repository.BookRepository;
import tip.repository.TagRepository;

@Controller
public class TestController {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BookRepository bookRepository;
    private boolean init = false;

    @GetMapping("/init")
    @Transactional
    public String init() {
        if (!init) {

            Book a = new Book("book a", "writer a", "isbn a", null);
            Book b = new Book("book b", "writer b", "isbn b", null);
            bookRepository.save(a);
            bookRepository.save(b);
            Tag ta = new Tag("tag a");
            Tag tb = new Tag("tag b");
            tagRepository.save(ta);
            tagRepository.save(tb);
            b.addTag(tb);
            a.addTag(ta);
            init = true;
        }

        return "redirect:/";
    }
}
