package ohtucli.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtucli.domain.Database;
import ohtucli.domain.Vinkki;

public class DatabaseVinkkiDao implements VinkkiDao {

    private Database db;

    public DatabaseVinkkiDao(Database db) {
        this.db = db;
    }

    @Override
    public List<Vinkki> listAll() {
        try {
            return db.queryAndCollect("SELECT * FROM Vinkki;", rs -> {
                return new Vinkki(
                        rs.getString("header"),
                        rs.getString("type"),
                        null);
            });
        } catch (SQLException ex) {
            System.out.println("error");
            return null;
        }

    }

    @Override
    public Vinkki findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Vinkki vinkki) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
