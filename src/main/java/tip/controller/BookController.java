
package tip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tip.repository.BookRepository;

@Controller
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @GetMapping("/")
    public String list(Model model) {
        
        return "index";
    }
}
