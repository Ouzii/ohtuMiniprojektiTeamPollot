package tip.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tip.domain.Tip;
import tip.domain.Detail;
import tip.domain.Tag;
import tip.repository.DetailRepository;
import tip.repository.TagRepository;
import tip.repository.TipRepository;

@Controller
public class TestController {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private DetailRepository detailRepository;
    private boolean init = false;

    @GetMapping("/init")
    @Transactional
    public String init() {
        if (!init) {

            Tip a = new Tip("book a", "book");

            Tip b = new Tip("book b", "book");
            tipRepository.save(a);
            tipRepository.save(b);

            Tag ta = new Tag("tag a");
            Tag tb = new Tag("tag b");

            Detail wa = new Detail("writer a");
            Detail wb = new Detail("writer b");
            Detail ia = new Detail("isbn a");
            Detail ib = new Detail("isbn d");
            detailRepository.save(wa);
            detailRepository.save(wb);
            detailRepository.save(ia);
            detailRepository.save(ib);

            tagRepository.save(ta);
            tagRepository.save(tb);
            b.addTag(tb);
            a.addTag(ta);
            a.addDetail("writer", wa);
            a.addDetail("isbn", ia);
            b.addDetail("isbn", ib);
            b.addDetail("writer", wb);
            init = true;
        }

        return "redirect:/";
    }
}
