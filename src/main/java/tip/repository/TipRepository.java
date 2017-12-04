
package tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.domain.Tip;

public interface TipRepository extends JpaRepository<Tip, Long> {
    public Tip findByName(String name);
}
