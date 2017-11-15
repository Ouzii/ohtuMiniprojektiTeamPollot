package ohtucli;

import ohtucli.data_access.InMemoryUserDao;
import ohtucli.data_access.UserDao;
import ohtucli.io.ConsoleIO;
import ohtucli.io.IO;
import ohtucli.services.AuthenticationService;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtucli.domain.Database;

public class App {

    private IO io;
    private AuthenticationService auth;

    public App(IO io, AuthenticationService auth) {
        try {
            this.io = io;
            this.auth = auth;
            Database db = new Database("jdbc:sqlite:testi.db", true);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void run() {
        while (true) {
            System.out.println("toimii");
            break;


        }
    }

    public static void main(String[] args) {
        UserDao dao = new InMemoryUserDao();
        IO io = new ConsoleIO();
        AuthenticationService auth = new AuthenticationService(dao);
        new App(io, auth).run();
    }
    
    // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
    // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
    //
    // UserDao dao = new InMemoryUserDao();  
    // StubIO io = new StubIO("new", "eero", "sala1nen" );   
    //  AuthenticationService auth = new AuthenticationService(dao);
    // new App(io, auth).run();
    // System.out.println(io.getPrints());
}
