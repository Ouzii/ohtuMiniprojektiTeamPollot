package ohtucli.data_access;

import ohtucli.domain.Vinkki;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements VinkkiDao {

    private List<Vinkki> vinkit;

    public InMemoryUserDao() {
        vinkit = new ArrayList<Vinkki>();
        vinkit.add(new Vinkki("pekka", "akkep", null));
    }        

    @Override
    public List<Vinkki> listAll() {
        return vinkit;
    }

   

    @Override
    public void add(Vinkki user) {
        vinkit.add(user);
    }

    public void setUsers(List<Vinkki> users) {
        this.vinkit = users;
    }

    public List<Vinkki> getUsers() {
        return vinkit;
    }

    @Override
    public Vinkki findById(int id) {
        return vinkit.get(id);
    }
}
