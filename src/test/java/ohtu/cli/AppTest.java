/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.cli;

import java.util.ArrayList;
import ohtu.cli.io.StubIO;
import ohtu.service.AppController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Beast
 */
public class AppTest {

    private App app;
    private StubIO io;
    private AppController appcontroller = new AppController();
    private ArrayList<String> commandlist;

    @Before
    public void setUp() {
        commandlist = new ArrayList<>();
        io = new StubIO(commandlist);
        this.app = new App(io, appcontroller);
    }

    @Test
    public void lisaaCommandWorks() throws InterruptedException {
        commandlist.add("lisaa");
        commandlist.add("nimi");
        commandlist.add("tyyppi");
        commandlist.add("lopeta");
        io = new StubIO(commandlist);
        this.app = new App(io, appcontroller);

        app.run();
    }

    @Test
    public void wrongCommandReturnsError() throws InterruptedException {
        commandlist.add("lisaasdadsasdasda");
        commandlist.add("lopeta");

        io = new StubIO(commandlist);
        this.app = new App(io, appcontroller);

        app.run();
        
        assertTrue(io.prints.contains("Väärä komento."));
    }

}
