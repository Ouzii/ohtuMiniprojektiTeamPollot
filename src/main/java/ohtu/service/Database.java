/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final Connection connection;

    public Database(String address) throws Exception {
        this.connection = DriverManager.getConnection(address);
        createTables();
        //createTestData();

    }

    public Connection getConnection() {
        return connection;
    }

    private void createTables() throws SQLException {
        System.out.println("entered create tables");
        Statement stmt = this.connection.createStatement();

        String sql = "PRAGMA foreign_keys = ON";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE if not exists Tip "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name varchar(32) NOT NULL,"
                + " type varchar(32) NOT NULL)";
        stmt.executeUpdate(sql);

        sql = "CREATE TABLE if not exists Detail "
                + "(v_id INTEGER,"
                + " type varchar(32) NOT NULL,"
                + " info varchar(256) NOT NULL,"
                + " FOREIGN KEY(v_id) REFERENCES Tip(id) ON DELETE CASCADE)";
        stmt.executeUpdate(sql);

        stmt.close();
        System.out.println("Tables exist or have been succesfully created");
    }

    private void createTestData() throws SQLException {
        Statement stmt = connection.createStatement();

        String sql = "INSERT INTO Tip (name, type) values('paavo','pesusieni')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Detail (v_id, type,info) values(1,'paavo','pesusieni')";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Detail (v_id, type,info) values(1,'paavo','pesusieni')";
        stmt.executeUpdate(sql);

        stmt.close();
        System.out.println("Test data added.");

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
