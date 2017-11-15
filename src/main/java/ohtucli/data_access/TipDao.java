package ohtucli.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtucli.domain.Database;
import ohtucli.domain.Tip;

public class TipDao implements Dao<Tip, Integer> {

    private Database database;

    public TipDao(Database database) {
        this.database = database;
    }

    @Override
    public Tip findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tip WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");

        Tip tip = new Tip(id);

        rs.close();
        stmt.close();
        connection.close();

        return tip;
    }

    @Override
    public List<Tip> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tip");

        ResultSet rs = stmt.executeQuery();
        List<Tip> tips = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");

            tips.add(new Tip(id));
        }

        rs.close();
        stmt.close();
        connection.close();

        return tips;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
