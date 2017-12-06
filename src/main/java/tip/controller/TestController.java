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

    @GetMapping("/init")
    @Transactional
    public String init() {
        if (tipRepository.count() == 0) {
            //core
            Tip blogPost = new Tip("Minun blogini luetuista kirjamerkeistä", "blogpost");
            blogPost.setRead(true);
            tipRepository.save(blogPost); 
            
            Tip booktrue = new Tip("Introduction to Algorithms", "book");
            booktrue.setRead(true);
            tipRepository.save(booktrue);         
            
            Tip video = new Tip("Jyrassic park 2", "video");
            video.setRead(false);          
            tipRepository.save(video);
            
            Tip bookfalse = new Tip("Introduction to the Theory of Computation", "book");
            bookfalse.setRead(false);
            tipRepository.save(bookfalse);
            
            Tip podcast = new Tip("hassun hauskat vitsit!", "podcast");
            video.setRead(false);          
            tipRepository.save(podcast);
            
            Tag tagA = new Tag("Käpistely");
            Tag tagB = new Tag("huumori");
            tagRepository.save(tagA);
            tagRepository.save(tagB);
            //tags
            bookfalse.addTag(tagB);
            video.addTag(tagB);
            podcast.addTag(tagB);
            bookfalse.addTag(tagA);
            booktrue.addTag(tagA);
            booktrue.addTag(tagB);
            
            
            
            

            Detail wa = new Detail("Thomas H. Cormen and others");
//            Detail waa = new Detail("Charles E. Leiserson");
//            Detail waaa = new Detail("Ronald L. Rivest");
//            Detail waaaa = new Detail("Clifford Stein");
//Tohon detail mappiin pitäis avaimella writer olla List<Detail> details arvona, 
//sillä kirjoittajia on useita? Runttaan nyt putkeen mutta hyvin kyseenalaista saattaa olla muutostarve
            Detail wb = new Detail("Michael Sipser");
            Detail ia = new Detail("9780262033848");
            Detail ib = new Detail("9780534950972");
            
            Detail yout = new Detail("https://youtube.com");
            Detail vit = new Detail("https://helsinginsirkus.fi");
            Detail minu = new Detail("https://minunblogi.fi");
            
            detailRepository.save(yout);
            detailRepository.save(vit);
            detailRepository.save(minu);
            
            detailRepository.save(wa);
            detailRepository.save(wb);
            detailRepository.save(ia);
            detailRepository.save(ib);
            booktrue.addDetail("writer", wa);
            booktrue.addDetail("isbn", ia);
            bookfalse.addDetail("isbn", ib);
            bookfalse.addDetail("writer", wb);
        
            
            blogPost.addDetail("url", minu);
            video.addDetail("url", yout);
            podcast.addDetail("url", vit);
            
            
        }

        return "redirect:/";
    }
}
