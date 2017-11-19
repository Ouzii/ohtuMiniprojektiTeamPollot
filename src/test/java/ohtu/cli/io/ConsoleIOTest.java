/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.cli.io;

import java.util.ArrayList;
import java.util.Scanner;
import ohtu.cli.App;
import ohtu.service.AppController;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author Beast
 */
public class ConsoleIOTest {

    Scanner scanner = new Scanner(System.in);
    private App app;
    private ConsoleIO io;
    private AppController appcontroller = new AppController();
    private ArrayList<String> commandlist;

    @Before
    public void setUp() {
        commandlist = new ArrayList<>();
        io = new ConsoleIO();
        this.app = new App(io, appcontroller);
    }

    @Test
    public void readIntMethodWorks() {
        //TODO
    }

    @Test
    public void readLineMethodWorks() {

        //Tämä testi ei toimi... 
//        ByteArrayInputStream in = new ByteArrayInputStream("Pekka\n".getBytes());
//        System.setIn(in);
//        
//
//        Assert.assertEquals(io.readLine("Kerro nimesi: "), "Pekka");
    }

}
