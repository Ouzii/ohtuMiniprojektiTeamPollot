package ohtucli;

import ohtucli.data_access.InMemoryUserDao;
import ohtucli.io.ConsoleIO;
import ohtucli.io.IO;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtucli.data_access.VinkkiDao;
import ohtucli.domain.Database;

public class App {

    private IO io;

    public App(IO io) {
        try {
            this.io = io;
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
        VinkkiDao dao = new InMemoryUserDao();
        IO io = new ConsoleIO();
        new App(io).run();
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
