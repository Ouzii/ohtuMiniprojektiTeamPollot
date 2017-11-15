package ohtucli;

import ohtucli.data_access.InMemoryUserDao;
import ohtucli.io.ConsoleIO;
import ohtucli.io.IO;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtucli.data_access.VinkkiDao;
import ohtucli.domain.Database;
import ohtucli.domain.Vinkki;

public class App {

    private IO io;
    private VinkkiDao dao;

    public App(IO io, VinkkiDao dao) {
        this.dao = dao;
        try {
            this.io = io;
            Database db = new Database("jdbc:sqlite:testi.db", true);
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
                    for (Vinkki vinkki : dao.listAll()) {
                        io.print("Otsikko: "+vinkki.getHeader());
                        io.print("Tyyppi: "+vinkki.getType());
                    }
                    break;
                case "lisaa":
                    String header = io.readLine("Otsikko:");
                    String type = io.readLine("Tyyppi:");
                    dao.add(new Vinkki(header, type, null));
                    break;
                default:
                    System.out.println("Väärä komento.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        VinkkiDao dao = new InMemoryUserDao();
        IO io = new ConsoleIO();
        new App(io, dao).run();
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

