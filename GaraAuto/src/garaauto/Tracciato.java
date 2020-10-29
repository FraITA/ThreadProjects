package garaauto;

import java.util.ArrayList;

public class Tracciato {

    public static int lunghezza;

    public static int nGiri;

    public static ArrayList<Automobile> auto;

    public static boolean garaFinita;

    public static int inizioGara;

    public static Pilota vincitore;

    public static void iniziaGara() {
    }

    public static boolean addAuto(Automobile auto) {
        return Tracciato.auto.add(auto);
    }

    public static void vittoria(Pilota pilota) {
    }

    public static void incidente() {
    }

    public static void fineGara() {
    }

    public static void setTracciato(int lunghezza, int nGiri) {
    }
}
