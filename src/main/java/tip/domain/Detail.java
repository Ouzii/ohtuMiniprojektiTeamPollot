package tip.domain;

import java.util.HashSet;
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
    @ManyToMany(mappedBy = "details", fetch = FetchType.EAGER)
    private Set<Tip> tips;

    public Detail(String name) {
        this.value = name;
    }

    public void addTip(Tip tip) {
        if (this.tips == null) {
            this.tips = new HashSet<>();
        }
        this.tips.add(tip);
    }

    public void removeTip(Tip tip) {
        this.tips.remove(tip);
    }

    @Override
    public String toString() {
        return this.value;
    }

    ///hashauksee
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Detail detail = (Detail) o;
        return this.getId() != null
                && Objects.equals(this.value, detail.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
