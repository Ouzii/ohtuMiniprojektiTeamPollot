
package tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.domain.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    
}
