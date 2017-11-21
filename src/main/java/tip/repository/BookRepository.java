
package tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
