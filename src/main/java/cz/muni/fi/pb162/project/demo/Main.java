package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.Chess;
import cz.muni.fi.pb162.project.Color;
import cz.muni.fi.pb162.project.Player;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Alzbeta Strompova
 */
public class Main {


    public static void main(String[] args) throws Exception {

        System.out.println("Hello world!");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");
        System.out.println(dtf.withLocale(Locale.ENGLISH).format(LocalTime.of(15, 0)));

        var player1 = new Player("Jozko", Color.WHITE);
        var player2 = new Player("Janko", Color.BLACK);

        var chess = new Chess(player1, player2);

        chess.setInitialSet();
        chess.write(System.out);
        chess.play();


    }

}
