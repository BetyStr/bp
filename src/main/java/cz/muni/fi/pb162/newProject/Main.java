package cz.muni.fi.pb162.newProject;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Alzbeta Strompova
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");

        System.out.println(dtf.withLocale(Locale.ENGLISH).format(LocalTime.of(0, 0)));
    }

}
