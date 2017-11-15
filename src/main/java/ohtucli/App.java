package ohtucli;

import java.util.List;
import ohtucli.data_access.InMemoryUserDao;
import ohtucli.io.ConsoleIO;
import ohtucli.io.IO;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtucli.data_access.DatabaseVinkkiDao;
import ohtucli.data_access.VinkkiDao;
import ohtucli.domain.Database;
import ohtucli.domain.Vinkki;

public class App {

    private IO io;

    public App(IO io) {
        try {
            this.io = io;
            Database db = new Database("jdbc:sqlite:testi.db", false);
            DatabaseVinkkiDao vdao = new DatabaseVinkkiDao(db);
            List<Vinkki> vinkit = vdao.listAll();
            for (Vinkki vinkki : vinkit) {
                System.out.println(vinkki);
            }
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        System.out.println("toimii");
        OUTER:
        while (true) {
            String input = io.readLine("Anna komento (listaa, lisaa, lopeta): ");
            switch (input) {
                case "lopeta":
                    break OUTER;
                case "listaa":
                    break;
                case "lisaa":
                    break;
                default:
                    System.out.println("Väärä komento.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
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
