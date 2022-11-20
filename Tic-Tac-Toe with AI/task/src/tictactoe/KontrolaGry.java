package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class KontrolaGry extends  AiHard{
    char[][] gra = new char[3][3];
    AiMedium aiMedium = new AiMedium();
    AiHard aiHard = new AiHard();
    static Scanner scanner = new Scanner(System.in);

    void graWTrakcie() {
        int c = 0;
        mapa.Mapa(gra); // tworzy mape
        boolean sprawdz = true;
        while (sprawdz) {
            String gracze = ktoGra();
            String[] uzytkownicy = gracze.split(" "); // Zapisuje liste graczy
            if (uzytkownicy.length == 1) {  // Jezeli lista graczy jest rowna 1 i zawiera exit to wyłacza program
                if (uzytkownicy[0].equals("exit")) {
                    System.exit(0);
                } else {                    // Inaczej źle wpisałeś graczy
                    System.out.println("Źle wpisałeś graczy! " +
                            "Wpisz ponownie!");
                }
            }
            else if (uzytkownicy.length == 3) { // Sprawdza czy dobrze wpisałeś graczy
                if (uzytkownicy[1].equals("user") || uzytkownicy[1].equals("easy") || uzytkownicy[1].equals("medium")
                       || uzytkownicy[1].equals("hard") && uzytkownicy[2].equals("user") || uzytkownicy[2].equals("easy")
                        || uzytkownicy[2].equals("medium") || uzytkownicy[2].equals("hard")) {
                    sprawdz = false;
                    mapa.drukujGre(gra);
                    while (true) {
                        if (Rezultat(c)) { // Sprawdza czy jest rezultat gry
                            break;
                        } else if (mapa.RuchX(gra) == 'X') { // Sprawdza czy rusza 'X'
                            zrobRuch(uzytkownicy[1], 'X', 'O'); // Uzytkownik z listy graczy pod indeksem 1 rusza
                            c++;
                        } else if (mapa.RuchX(gra) == 'O') {  // Sprawdza czy rusza 'O'
                            zrobRuch(uzytkownicy[2], 'O', 'X'); // Uzytkownik z listy graczy pod indeksem 2 rusza
                            c++;
                        }
                    }
                } else {
                    System.out.println("Źle wpisałeś graczy! " +
                            "Wpisz ponownie!");
                }
            } else {
                System.out.println("Źle wpisałeś graczy! " +
                        "Wpisz ponownie!");

            }
        }
    }
    String ktoGra() {
        System.out.println("Wpisz kto gra, np \"start user medium\" ");
        return scanner.nextLine();
    }
    void ruchGracza(char x) { // Jezeli gra gracz to jego ruch + wydrukuj mape
        mapa.gramyNaZasadach(gra, x);
        mapa.drukujGre(gra);
    }
    void ruchAIEasy(char x) {  // Jezeli gra easy bot to jego ruch + wydrukuj mape
        ruchAI(gra, x);
        mapa.drukujGre(gra);
    }
    void ruchAIMedium(char x, char OX) { // Jezeli gra medium bot to jego ruch + wydrukuj mape
        aiMedium.ruchAI(gra, x, OX);
        mapa.drukujGre(gra);
    }
    void ruchAIHard(char a, char OX) { // Jezeli gra hard bot to jego ruch + wydrukuj mape
        aiHard.ruchAI(gra, a, OX);
        mapa.drukujGre(gra);
    }

    // Sprawdza kto ma grać
    void zrobRuch(String uzytkownik, char x, char OX) {
        if (Objects.equals(uzytkownik, "user")) {
            ruchGracza(x);
        }
        if (Objects.equals(uzytkownik, "easy")) {
            System.out.println("Ruch bot: \"easy\"");
            ruchAIEasy(x);
        }
        if (Objects.equals(uzytkownik, "medium")) {
            System.out.println("Ruch bot: \"medium\"");
            ruchAIMedium(x, OX);
        }
        if (Objects.equals(uzytkownik, "hard")) {
            System.out.println("Ruch bot: \"hard\"");
            ruchAIHard(x, OX);
        }
    }
    boolean Rezultat(int c) { // Sprawdza czy koniec gry oraz kto wygrywa
        if (mapa.Wygrany(gra, 'X')) {
            System.out.println("X wygrywa");
            return true;
        } else if (mapa.Wygrany(gra, 'O')) {
            System.out.println("O wygrywa");
            return true;
        } else if (c == 9) {
            System.out.println("Remis");
            return true;
        }
        return false;
    }

}
