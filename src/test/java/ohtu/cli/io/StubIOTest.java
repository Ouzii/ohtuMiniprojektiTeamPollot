/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.cli.io;

import java.util.ArrayList;
import ohtu.cli.App;
import ohtu.service.AppController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Beast
 */
public class StubIOTest {

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
    public void readIntMethodWorks() {
        io.lines.add("2");
        Assert.assertEquals(io.readInt("Anna kissojen lukumäärä: "),2);
    }

}
