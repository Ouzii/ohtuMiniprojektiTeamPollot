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

    public Book(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }
}
