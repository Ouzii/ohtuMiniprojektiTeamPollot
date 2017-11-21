package tip.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book extends AbstractPersistable<Long> {

    private String name;
    private String isbn;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Tag> tags;

    public Book(String name, String isbn) { // TARVITAAN VIESTI JOKA VALITETAAN
                                            // ETEENPAIN MIKALI ISBN EI KAY ?
        if (isbn.matches("^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ])" 
                + "{4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]"
                + "+[- ]?[0-9]$")) {
            this.name = name;
            this.isbn = isbn;
        } else {
            // Tähän, joku toiminto, joka asettaa johonkin muuttujaan
            // tiedon siitä, että lisääminen ei onnistunut, koska 
            // invaliidi syöte. amrite?
        }
    }
}
