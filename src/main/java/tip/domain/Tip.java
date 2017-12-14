package tip.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
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
public class Tip extends AbstractPersistable<Long> {

    private String name;
    private String type;
    private boolean read;
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    private Map<String, Detail> details;

    public Tip(String name, String type) {
        this.name = name;
        this.type = type;
        this.tags = new HashSet();
        this.details = new TreeMap();
    }

    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new HashSet();
        }
        this.tags.add(tag);
    }

    public void addDetail(String key, Detail detail) {
        if (this.details == null) {
            this.details = new TreeMap<>();
        }
        if (detail == null || detail.getValue() == null || detail.getValue().trim().isEmpty()) {
            return;
        }
        this.details.put(key, detail);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void removeDetailByKey(String key) {
        if (details.containsKey(key)) {
            details.remove(key);
        }
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
        Tip tip = (Tip) o;
        return this.getId() != null
                && Objects.equals(this.getId(), tip.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
