package tictactoe;

import java.util.Scanner;

public class Mapa {
    public void Mapa(char[][] gra) { // tworzy mape
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gra[i][j] = ' ';
            }
        }
    }

    void drukujGre(char[][] gra) { // Drukuje mape
        System.out.println("---------");
        System.out.println("| " + gra[0][0] + " " + gra[0][1] + " " + gra[0][2] + " |");
        System.out.println("| " + gra[1][0] + " " + gra[1][1] + " " + gra[1][2] + " |");
        System.out.println("| " + gra[2][0] + " " + gra[2][1] + " " + gra[2][2] + " |");
        System.out.println("---------");
    }

     boolean CzyZajete(char[][] gra, int wspl, int wspal) { // Sprawdza czy zajete
        if (gra[wspl - 1][wspal - 1] == 'X' || gra[wspl - 1][wspal - 1] == 'O') {
            return false;
        }
        return true;
    }

    boolean Koordynaty(int wspl, int wspal) { // sprawdza czy wspolrzedne nie sa poza mapa
        if (wspal < 1 || wspal > 3 || wspl < 1 || wspl > 3) {
            return false;
        }
        return true;
    }
     void gramyNaZasadach(char[][] gra, char a) { // Ruch gracza
        System.out.println("Wpisz koordynaty np(2 2) ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int wspolrzednaX = scanner.nextInt();
            int wspolrzednaY = scanner.nextInt();
            if (Koordynaty(wspolrzednaX, wspolrzednaY)) {
                if (CzyZajete(gra, wspolrzednaX, wspolrzednaY)) {
                    if (gra[wspolrzednaX - 1][wspolrzednaY - 1] == ' ') {
                        gra[wspolrzednaX - 1][wspolrzednaY - 1] = a;
                    }
                } else {
                    System.out.println("To miejsce jest zajęte!" +
                            "\nWybierz inne!");
                    gramyNaZasadach(gra, a);
                }
            } else {
                System.out.println("Koordynaty powinny być od 1 do 3!");
                gramyNaZasadach(gra, a);
            }
        } else {
            scanner.nextLine();
            System.out.println("Powinienes wpisac cyfre!");
        }
    }
    char RuchX(char[][]gra) {  // Liczy X oraz O i sprawdza czyj jest ruch
        int liczX = 0;
        int liczO = 0;
        for (int i = 0; i < gra.length; i++) {
            for (int j = 0; j < gra.length; j++) {
                if (gra[i][j] == 'X') {
                    liczX++;
                }
                if (gra[i][j] == 'O') {
                    liczO++;
                }
            }
        }
        if (liczO >= liczX) {
            return 'X';
        }
        else {
            return 'O';
        }
    }
     Boolean Wygrany(char[][] gra, char OX) { // Sprawdza kombinacje na wygrana gre
        return (gra[0][0] == OX && gra[0][1] == OX && gra[0][2] == OX ||
                gra[1][0] == OX && gra[1][1] == OX && gra[1][2] == OX ||
                gra[2][0] == OX && gra[2][1] == OX && gra[2][2] == OX ||
                gra[0][0] == OX && gra[1][1] == OX && gra[2][2] == OX ||
                gra[2][0] == OX && gra[1][1] == OX && gra[0][2] == OX ||
                gra[0][0] == OX && gra[1][0] == OX && gra[2][0] == OX ||
                gra[0][1] == OX && gra[1][1] == OX && gra[2][1] == OX ||
                gra[0][2] == OX && gra[1][2] == OX && gra[2][2] == OX);
    }

}
