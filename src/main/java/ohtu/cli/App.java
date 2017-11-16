package ohtu.cli;

import java.util.List;
import ohtu.cli.io.ConsoleIO;
import ohtu.cli.io.IO;
import ohtu.service.AppController;
import ohtu.service.Tip;

public class App {

    private IO io;
    private AppController app;

    public App(IO io, AppController appcontroller) {
        this.app = appcontroller;
        this.io = io;
    }

    public void run() {
        System.out.println("toimii");
        OUTER:
        while (true) {
            String input = io.readLine("Anna komento (listaa, lisaa, lopeta): ");
            switch (input) { // Nämä caset lopeta lukuunottamatta voidaan
                             // siirtää myöhemmin tämän luokan metodeiksi. 
                case "lopeta":
                    break OUTER;
                case "listaa":
                    
                    for (Tip t : app.getTips()) {
                        
                        System.out.println(t.toString());
                    }
                    break;
                case "lisaa":
                    String name = io.readLine("Anna nimi: ");
                    String type = io.readLine("Anna tyyppi: ");
                    app.addTip(name, type);
                    break;
                default:
                    System.out.println("Väärä komento.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        AppController app = new AppController();

        new App(io, app).run();
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
