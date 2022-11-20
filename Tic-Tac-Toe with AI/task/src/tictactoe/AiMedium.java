package tictactoe;

import java.util.Random;

public class AiMedium extends AiEasy{
    void ruchAI(char[][] gra, char a, char OX) {

        int wspolrzednaX;
        int wspolrzednaY;
        Random random = new Random();
        String[] XO = ruchKonczacy(gra, a, OX).split(" ");
        if (XO[0].equals("")) { // Sprawdza czy nie ma mozliwej blokujacej/wygrywajacej kombinacji
             wspolrzednaX = random.nextInt(1, 4);
             wspolrzednaY = random.nextInt(1, 4);
        }
        else {    // Jezeli da sie wygrac/zablokowac przeciwnika to zrob to
             wspolrzednaX = Integer.parseInt(XO[0]);
             wspolrzednaY = Integer.parseInt(XO[1]);
             wspolrzednaX += 1;
             wspolrzednaY += 1;
        }
                if (mapa.CzyZajete(gra, wspolrzednaX, wspolrzednaY)) {
                    if (gra[wspolrzednaX - 1][wspolrzednaY - 1] == ' ') {
                        gra[wspolrzednaX - 1][wspolrzednaY - 1] = a;
                    }
                } else
                    ruchAI(gra, a, OX);

        }
    String ruchKonczacy(char[][] gra, char a, char OX) {
        int liczPoziomo = 0;
        int liczPionowo = 0;
        int liczPozW = 0;
        int liczPionW = 0;
        String ukos;
        for (int i = 0; i < gra.length; i++) {
            for (int j = 0; j < gra.length; j++) {
                if (gra[i][j] == OX) { // Sprawdza ile jest O/X w rzędzie
                    liczPoziomo++;
                }
                if (gra[i][j] == a)  { // Sprawdza ile jest O/X w poziomie
                    liczPozW++;
                }
                if (gra[j][i] == OX) {
                    liczPionowo++;
                }
                if (gra[j][i] == a) {
                    liczPionW++;
                }
                if (liczPoziomo == 2 || liczPozW == 2) { // Jezeli moze wygrac/zablokowac wygrywajacy ruch to niech zwroci wspolrzedne
                    for (int k = 0; k < gra.length; k++) {
                        if (gra[i][k] == ' ') {
                            return i + " " + k;
                        }
                    }
                }
                if (liczPionowo == 2 || liczPionW == 2) { // Jezeli moze wygrac/zablokowac wygrywajacy ruch to niech zwroci wspolrzedne
                    for (int k = 0; k < gra.length; k++) {
                        if (gra[k][i] == ' ') {
                            return k + " " + i;
                        }
                    }
                }
            }
            liczPozW = 0;
            liczPionW = 0;
            liczPoziomo = 0;
            liczPionowo = 0;
        } ukos = czyNaUkos(gra, a, OX);
        if (ukos.equals("")) { // Jezeli Nie ma wygrywajacego/blokujacego ruchu w ukosie to zwroc ""
            return "";
        }
        else {
            return ukos;  // Inaczej zwroc string
        }
    }
    String czyNaUkos(char[][] gra, char a, char OX) { // Sprawdza mozliwe kombinacje na wygrana w ukosie
        if (gra[0][0] == OX && gra[1][1] == OX && gra[2][2] == ' ' || gra[0][0] == a && gra[1][1] == a && gra[2][2] == ' ') {
            return "2 2";
        }
        if (gra[1][1] == OX && gra[2][2] == OX && gra[0][0] == ' ' || gra[1][1] == a && gra[2][2] == a && gra[0][0] == ' ') {
            return "0 0";
        }
        if (gra[0][0] == OX && gra[2][2] == OX && gra[1][1] == ' ' || gra[0][0] == a && gra[2][2] == a && gra[1][1] == ' ') {
            return "1 1";
        }
        if (gra[2][0] == OX && gra[1][1] == OX && gra[0][2] == ' ' || gra[2][0] == a && gra[1][1] == a && gra[0][2] == ' ') {
            return "0 2";
        }
        if (gra[2][0] == ' ' && gra[1][1] == OX && gra[0][2] == OX || gra[2][0] == ' ' && gra[1][1] == a && gra[0][2] == a) {
            return "2 0";
        }
        if (gra[2][0] == OX && gra[1][1] == ' ' && gra[0][2] == OX || gra[2][0] == a && gra[1][1] == ' ' && gra[0][2] == a) {
            return "1 1";
        } else {
            return ""; // Jezeli nie ma zadnej blokujacej/wygrywajacej kombinacji to zwroc ""
        }
    }
}
