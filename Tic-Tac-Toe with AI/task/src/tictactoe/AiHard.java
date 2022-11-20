package tictactoe;

public class AiHard extends  AiMedium{

    boolean ruchy(char gra[][]) { // Sprawdza czy są jeszcze jakieś ruchy
        for (int i = 0; i < gra.length; i++) {
            for (int j = 0; j < gra.length; j++) {
                if (gra[i][j] == ' ') {
                    return true;
                }
            }
        } return false;
    }
    int ocenaGry(char gra[][], char a, char OX) { //
        for (int i = 0; i < gra.length; i++) {
            if (gra[i][0] == gra[i][1] && gra[i][1] == gra[i][2]) {
                if (gra[i][0] == a) { // Sprawdza rzędy dla X oraz O
                    return +10;      // jezeli wygrywa to +10
                } else if (gra[i][0] == OX) {
                    return -10;      // jezeli przegrywa to +10
                }
            }
        }
        for (int i = 0; i < gra.length; i++) { // Sprawdza kolumny dla X oraz O
            if (gra[0][i] == gra[1][i] && gra[1][i] == gra[2][i]) {
                if (gra[0][i] == a) {
                    return +10;       // jezeli wygrywa to +10
                } else if (gra[0][i] == OX) {
                    return -10;      // jezeli przegrywa to +10
                }
            }
        }
        if (gra[0][0] == gra[1][1] & gra[1][1] == gra[2][2]) { // Sprawdza ukos dla X oraz O
            if (gra[0][0] == a) {
                return +10;          // jezeli wygrywa to +10
            } else if (gra[0][0] == OX) {
                return -10;         // jezeli przegrywa to +10
            }
        }
        if (gra[0][2] == gra[1][1] && gra[1][1] == gra[2][0]) {
            if (gra[0][2] == a) {
                return +10;        // jezeli wygrywa to +10
            } else if (gra[0][2] == OX) {
                return -10;         // jezeli przegrywa to +10
            }
        }
        return 0; // Jezeli nikt nie wygrywa to zwraca 0
    }
    // Funkcja sprawdza wszystkie mozliwe ruchy i zwraca wartość najlepszego
    int minMax(char gra[][], int dlugosc, Boolean czyMax, char a, char OX) {
        int wynik = ocenaGry(gra, a, OX);
        if (wynik == 10)   // Jezeli wygrywa to zwraca jego wartość
            return wynik;
        if (wynik == -10)  // Jezeli przegrywa to ...
            return wynik;
        if (!ruchy(gra)) {  // Jezeli nie ma ruchow to zwraca 0
            return 0;
        }
        if(czyMax) {    // Jezeli wygrywający
            int najlepszyRuch = -1000;
            for (int i = 0; i < gra.length; i++) {
                for (int j = 0; j < gra.length; j++) {
                    if (gra[i][j] == ' ') {
                        gra[i][j] = a;
                        // Szukaj rekurencją najlepszego ruchu
                        najlepszyRuch = Math.max(najlepszyRuch, minMax(gra, dlugosc + 1, !czyMax, a, OX));
                        gra[i][j] = ' ';
                    }
                }
            } return najlepszyRuch;
        }
        else {   // Jezeli przegrywający
            int najlepszyRuch = 1000;
            for (int i = 0; i < gra.length; i++) {
                for (int j = 0; j < gra.length; j++) {
                    if (gra[i][j] == ' ') {
                        gra[i][j] = OX;
                        najlepszyRuch = Math.min(najlepszyRuch, minMax(gra, dlugosc + 1, !czyMax, a, OX));
                        gra[i][j] = ' ';
                    }
                }
            }return najlepszyRuch;
        }
    }
    String znajdzNajlepszyRuch(char[][] gra, char a, char OX) {
        // Zwraca najlepszy mozliwy ruch do Stringa
        int najlepszy = -1000;
        int najlepszyRuchRzad = -1;
        int najlepszyRuchKolumna = -1;
        // Szuka i zwraca najlepszy ruch wśród całej gry
        for (int i = 0; i < gra.length; i++) {
            for (int j = 0; j < gra.length; j++) {
                if (gra[i][j] == ' ') {
                    gra[i][j] = a;
                    int ruch = minMax(gra, 0, false, a, OX);
                    gra[i][j] = ' ';
                    if (ruch > najlepszy) {  // Nadpisuje ruch z lepszym ruchem
                        najlepszyRuchRzad = i;
                        najlepszyRuchKolumna = j;
                        najlepszy = ruch;
                    }
                }
            }
        }
        return najlepszyRuchRzad + " " + najlepszyRuchKolumna; // Zwraca wspolrzedna + " " + wspolrzedna

    }
    void ruchAI(char[][] gra, char a, char OX) {
        String[] ruchy = znajdzNajlepszyRuch(gra, a, OX).split(" "); // Zapisywanie stringa z wspolrzednymi do tablicy
        int wspolrzednaX = Integer.parseInt(ruchy[0]) + 1; // parsowanie do inta
        int wspolrzednaY = Integer.parseInt(ruchy[1]) + 1;
        gra[wspolrzednaX - 1][wspolrzednaY - 1] = a; // Ruch

    }
}
