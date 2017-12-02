package tip.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Detail extends AbstractPersistable<Long> {

    private String value;
    @ManyToMany(mappedBy="details",fetch = FetchType.EAGER)
    private Set<Tip> books;

    public Detail(String name) {
        this.value = name;
    }

    public void addTip(Tip book) {
        if (this.books == null) {
            this.books = new HashSet<>();
        }
        this.books.add(book);
    }
    
    public void removeTip(Tip book) {
        this.books.remove(book);
    }
    
    @Override
    public String toString() {
        return this.value;
    }
    
        ///hashauksee
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return this.getId() != null 
                && Objects.equals(this.value, detail.value);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
