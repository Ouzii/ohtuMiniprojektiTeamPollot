
package ohtucli.data_access;

import java.util.List;
import ohtucli.domain.Vinkki;

public interface VinkkiDao {
    List<Vinkki> listAll();
    Vinkki findById(int id);
    void add(Vinkki vinkki);
}
