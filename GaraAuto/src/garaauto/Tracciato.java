package garaauto;

import java.util.ArrayList;

public class Tracciato {

    public static int lunghezza;

    public static int nGiri;

    public static ArrayList<Thread> arrayAuto;

    public static boolean garaFinita;

    public static int inizioGara;

    public static Pilota vincitore;

    public static void iniziaGara() {
        for(Thread a : arrayAuto){
            a.start();
        }
    }

    public static boolean addAuto(Thread auto) {
        return Tracciato.arrayAuto.add(auto);
    }

    public static void vittoria(Pilota pilota) {
    }

    public static void incidente() {
    }

    public static void fineGara() {
    }

    public static void setTracciato(int lunghezza, int nGiri) {
        arrayAuto = new ArrayList<>();
        Tracciato.lunghezza = lunghezza;
        Tracciato.nGiri = nGiri;
    }

    public static int getLunghezza() {
        return lunghezza;
    }

    public static int getnGiri() {
        return nGiri;
    }

    public static ArrayList<Thread> getArrayAuto() {
        return arrayAuto;
    }

    public static boolean isGaraFinita() {
        return garaFinita;
    }

    public static int getInizioGara() {
        return inizioGara;
    }

    public static Pilota getVincitore() {
        return vincitore;
    }
}
