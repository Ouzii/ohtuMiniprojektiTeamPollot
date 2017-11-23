package tip.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "books",
            joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Book(String name, String writer, String isbn) {
        this.name = name;
        this.isbn = isbn;
        this.writer = writer;
        this.tags = new HashSet();
    }

    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new HashSet();
        }
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
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
        if (this.isbn == null) {
            return false;
        }

        String tmp = this.isbn;
        tmp = tmp.replaceAll("-", "");
        if (tmp.length() != 13) {
            return false;
        }

        int num = 0, total = 0;
        for (int i = 1; i <= 12; i++) {
            num = Integer.parseInt(tmp.substring(i - 1, i));
            total += ((i - 1) % 2 == 0) ? num * 1 : num * 3;
        }
        int chksum = 10 - (total % 10);
        if (chksum == 10) {
            chksum = 0;
        }
        return chksum == Integer.parseInt(tmp.substring(12));
    }
    
    ///hashauksee
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.getId() != null 
                && Objects.equals(this.getId(), book.getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
