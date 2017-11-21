package tip.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //nimestselviää mitä luo
@NoArgsConstructor //nimestselviää mitä luo
@Data //getterit ja setterit
@Entity //luo tietokantataulu
public class Book extends AbstractPersistable<Long> {

    private String name;
    private String writer;
    private String isbn;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Tag> tags;
    
    public Book(String name,String writer, String isbn) {
        this.name = name;
        this.isbn = isbn;
        this.writer = writer;
    }
    //writer voi olla null 
    //validointi tästä
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (!validateISBN()) {
            errors.add("ISBN ei ole muodossa ISBN13!");
        }
        if (name == null || name.trim().isEmpty()) {
            errors.add("nimimerkin nimi ei saa olla tyhjä");
        }
        return errors;
    }
    
    private boolean validateISBN() {
        return isbn.matches("^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ])"
                + "{4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]"
                + "+[- ]?[0-9]$");
    }
}
