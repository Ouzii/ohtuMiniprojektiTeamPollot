package tip.domain;

import javax.persistence.Entity;
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
    
}
