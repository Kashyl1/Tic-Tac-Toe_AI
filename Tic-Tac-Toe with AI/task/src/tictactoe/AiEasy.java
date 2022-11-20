package tictactoe;

import java.util.Random;

public class AiEasy {
    Mapa mapa = new Mapa();

    void ruchAI(char[][] gra, char a) {
        Random random = new Random();
        int wspolrzednaX = random.nextInt(1, 4);  // Szuka losowych koordynatow w zasiegu mapy
        int wspolrzednaY = random.nextInt(1, 4);
            if (mapa.CzyZajete(gra, wspolrzednaX, wspolrzednaY)) {  // sprawdza czy koordynaty sa zajete
                gra[wspolrzednaX - 1][wspolrzednaY - 1] = a;  // Jesli nie to rusza
            } else
                ruchAI(gra, a);  // Jezeli zajete to szukaj koordynatow od nowa

    }
}
