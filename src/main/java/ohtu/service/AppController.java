/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruupert
 *
 * Sets the application environment up
 */
public class AppController {

    private Database db;

    public AppController() {
        try {
            this.db = new Database("jdbc:sqlite:testi.db");
        } catch (Exception ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    public AppController(String address) {
        try {
            this.db = new Database(address);
        } catch (Exception ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    private void init() {

    }

    public void addTip(String name, String type) {
        try {
            PreparedStatement prep = this.db.getConnection().prepareStatement(
                    "INSERT INTO Tip (name, type) values(?, ?)");

            prep.setString(1, name);
            prep.setString(2, type);
            prep.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Tip> getTips() {
        System.out.print("entering AppController.getTips()");
        ArrayList<Tip> rows = new ArrayList<>();
        Tip tmp;
        try {
            Statement stmt = db.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from Tip");

            while (rs.next()) {
                tmp = new Tip();
                tmp.setId(rs.getInt("id"));
                tmp.setName(rs.getString("name"));
                tmp.setType(rs.getString("type"));
                rows.add(tmp);
            }

        } catch (SQLException e) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("... done\n and now returning result");
        return rows;
    }
    /*  // tarjoiluehdotus:
    private List<Tip> getTips(String user) throws SQLException {
        List<Tip> rows = new ArrayList<>();
        Tip tmp;

        PreparedStatement prep = this.db.getConnection().prepareStatement(
                "select * from tips where user_id = ?;");
        prep.addBatch(user);

        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            tmp = new Tip();
            tmp.setId(rs.getInt("id"));
            tmp.setName(rs.getString("name"));
            tmp.setType(rs.getString("type"));
            rows.add(tmp);
        }

        return rows;
    } */

}
