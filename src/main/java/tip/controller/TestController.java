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

            Tip a = new Tip("Introduction to Algorithms", "book");

            Tip b = new Tip("Introduction to the Theory of Computation", "book");
            tipRepository.save(a);
            tipRepository.save(b);

            Tag ta = new Tag("tag a");
            Tag tb = new Tag("tag b");

            Detail wa = new Detail("Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein");
//            Detail waa = new Detail("Charles E. Leiserson");
//            Detail waaa = new Detail("Ronald L. Rivest");
//            Detail waaaa = new Detail("Clifford Stein");
//Tohon detail mappiin pitäis avaimella writer olla List<Detail> details arvona, 
//sillä kirjoittajia on useita? Runttaan nyt putkeen mutta hyvin kyseenalaista saattaa olla muutostarve
            Detail wb = new Detail("Michael Sipser");
            Detail ia = new Detail("9780262033848");
            Detail ib = new Detail("9780534950972");
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
