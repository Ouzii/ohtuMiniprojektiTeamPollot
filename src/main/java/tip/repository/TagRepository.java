package tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByName(String name);
}
