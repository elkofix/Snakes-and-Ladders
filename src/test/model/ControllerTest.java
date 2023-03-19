package test;

import model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Controller;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller control;
    @BeforeEach
    public void setup() {
        control = new Controller();
    }
    @Test
    public void testPrintScores() {
        control.getScores().insert(new Node(21));
        control.getScores().insert(new Node(40));
        control.getScores().insert(new Node(60));
        control.getScores().insert(new Node(80));
        control.getScores().insert(new Node(100));
        String result = "Lista de puntajes \n"+
                "1: 100\n"+
                "2: 80\n"+
                "3: 60\n"+
                "4: 40\n"+
                "5: 21\n";
        assertEquals(result, control.printScoreList());
    }

    @Test
    public void testPrintOneScore() {
        control.getScores().insert(new Node(100));
        String result = "Lista de puntajes \n"+
                "1: 100\n";
        assertEquals(result, control.printScoreList());
    }
}