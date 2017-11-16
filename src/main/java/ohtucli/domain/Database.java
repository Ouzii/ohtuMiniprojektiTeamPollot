package ohtucli.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final boolean debug = true;
    private final Connection connection;

    public Database(String address, boolean reset) throws Exception {
        this.connection = DriverManager.getConnection(address);
        Statement stmt = connection.createStatement();
        if (reset) {
            reset();
        } else {
            init();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void init() {
        try {
            createTables();
            createData();
        } catch (SQLException e) {
            System.out.println("Unable to create table. Maybe it already exists.");
            return;
        }

    }

    public void createTables() throws SQLException {
        Statement stmt = connection.createStatement();

        String sql = "PRAGMA foreign_keys = ON";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE Tip "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name varchar(32) NOT NULL,"
                + " type varchar(32) NOT NULL)";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE Detail "
                + "(v_id INTEGER,"
                + " type varchar(32) NOT NULL,"
                + " info varchar(256) NOT NULL,"
                + " FOREIGN KEY(v_id) REFERENCES Tip(id) ON DELETE CASCADE)";
        stmt.executeUpdate(sql);

        stmt.close();
        System.out.println("Tables has been created");
    }

    private void createData() throws SQLException {
        Statement stmt = connection.createStatement();

        String sql = "INSERT INTO Tip (id, name, type) values(1, 'paavo','pesusieni')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Detail (v_id, type,info) values(1,'paavo','pesusieni')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Detail (v_id, type,info) values(1,'paavo','pesusieni')";
        stmt.executeUpdate(sql);

        stmt.close();
        System.out.println("Tables has been erased");
        //testi data
        /* INSERT INTO Vinkki (name,type) values('asd','asd');
         */
    }

    public void reset() {
        try {
            deleteTables();
            createTables();
            createData();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private void deleteTables() {
        try {
            Statement stmt = connection.createStatement();

            String sql = "DROP TABLE Tip";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE Detail";
            stmt.executeUpdate(sql);

            stmt.close();
            System.out.println("Tables has been erased");
        } catch (SQLException e) {
            System.out.println("database already empty");
        }
    }
    
    public <T> List<T> queryAndCollect(String query, Collector col) throws SQLException {
        List<T> rows = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            rows.add((T) col.collect(rs));
        }
        rs.close();
        stmt.close();
        return rows;
    }
    
    
}
