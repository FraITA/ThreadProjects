package garaauto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che imita un tracciato automobilistico.
 * @author FraITA
 */
public class Tracciato {

    /**
     * Lunghezza del tracciato.
     */
    private static int lunghezza;

    /**
     * Numero di giri totali da compiere per vincere la gara.
     */
    private static int nGiri;

    /**
     * HashMap che contiene tutti i Thread che sono le automobili in corsa.
     */
    private static HashMap<String, Thread> mapThread;
    
    /**
     * HashMap che contiene tutte le auto e tutti i loro dati.
     */
    private static HashMap<String, Automobile> mapAuto;

    /**
     * Tempo di inizio della gara.
     */
    private static int inizioGara;

    /**
     * Pilota vincitore della gara.
     */
    private static Pilota vincitore;
    
    /**
     * Variabile booleana che segna se il tracciato è stato settato.
     */
    private static boolean isSet = false;
    
    /**
     * ArrayList dei piloti ordinati secondo l'arrivo.
     */
    private static ArrayList<Pilota> classifica;
    
    /**
     * Numero di auto totali che stanno sul tracciato.
     */
    private static int nAuto;

    /**
     * Metodo che da l'inizio alla gara e attente un tot di tempo per
     * l'incidente.
     */
    public static void iniziaGara() {
        int tempoIncidente;
        int tMax = 60;
        int tMin = 10;
        
        if(!isSet){
            System.out.println("Tracciato non settato!");
            return;
        }
        
        if(mapAuto.isEmpty()){
            System.out.println("Auto non aggiunte!");
            return;
        }
        
        for (String key : Tracciato.mapThread.keySet()) {
            mapThread.get(key).start();
        }

        Tracciato.inizioGara = (int) System.currentTimeMillis();

        tempoIncidente = (int) (Math.random() * (tMax - tMin) + tMin);
        try {
            Thread.sleep(tempoIncidente*1000);
        } catch (InterruptedException ex) {
            System.out.println("Thread interrotto");
        }
        
        if(!mapThread.isEmpty()){
            incidente();
        }

    }

    public static boolean addAuto(Automobile auto) {
        Tracciato.mapAuto.put(auto.getScuderia(), auto);
        
        if(auto instanceof Auto){
            Tracciato.mapThread.put(auto.getScuderia(), (Thread) auto);
        }else if(auto instanceof AutoSostituto){
            Tracciato.mapThread.put(auto.getScuderia(), new Thread(auto, auto.getScuderia()));
        }
        
        nAuto = mapAuto.size();
        
        return true;
    }

    /**
     * Metodo che segna il vincitore e mette tutti i piloti che 
     * finiscono in una classifica.
     * @param pilota pilota che ha finito la gara.
     */
    protected static void fineGara(Pilota pilota) {
        if(Tracciato.vincitore == null){
            Tracciato.vincitore = pilota;
            System.out.println("\nAbbiamo un vincitore! \n");
        }
        classifica.add(pilota);
        mapAuto.remove(pilota.getAuto().getScuderia());
        mapThread.remove(pilota.getAuto().getScuderia());
        
        if(classifica.size() == nAuto){
            System.out.println("Classifica:\n");
            for(Pilota p : classifica){
                System.out.println("Posto " + (classifica.indexOf(p)+1) + " : " + p.getNome());
            }
        }
    }

    /**
     * Metodo che mette in atto un incidente, scegliendo qual è l'auto
     * che subisce l'incidente, rallenta le auto in gara e gestisce la
     * safety car.
     */
    private static void incidente() {
        Random generatore = new Random();
        
        Object[] array = mapAuto.keySet().toArray();
                
        String key = (String) array[generatore.nextInt(array.length)];
        
        Automobile auto = (Automobile) mapAuto.get(key);
        
        AutoSostituto autoS = new AutoSostituto("Scuderia sostitutiva", auto.getPilota(), 25, 50);
        
        System.out.println("\n" + auto.getPilota().getNome() + " ha subito un incidente!\n");
        
        auto.getPilota().setAuto(autoS);
        
        addAuto(autoS);
        
        mapAuto.remove(auto.getScuderia());
        mapThread.remove(auto.getScuderia());
        auto.setInGara(false);
        
        mapThread.get(autoS.getScuderia()).start();
        
        for(String k : mapAuto.keySet()){
            mapThread.get(k).setPriority(2);
            mapAuto.get(k).setVMin(25);
            mapAuto.get(k).setVMax(50);
        }
        
        addAuto(new SafetyCar("Safety Car", new Pilota("Pilota safety car"), 50, 55));
        
        mapThread.get("Safety Car").setPriority(10);
        
        int max = 0;
        for(String k : mapAuto.keySet()){
            int dist = mapAuto.get(k).getPilota().getDistPercorsa();
            if(dist > max){
                max = dist;
            }
        }
        
        mapAuto.get("Safety Car").getPilota().setDistPercorsa(max);
        mapThread.get("Safety Car").start();
        try {
            mapThread.get("Safety Car").join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tracciato.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mapAuto.remove("Safety Car");
        mapThread.remove("Safety Car");
        
        System.out.println("\n INCIDENTE TERMINATO \n");
        
        for(String k : mapAuto.keySet()){
            mapThread.get(k).setPriority(5);
            mapAuto.get(k).setVMin(100);
            mapAuto.get(k).setVMax(150);
        }
    }

    /**
     * Metodo che prepara il tracciato.
     * @param lunghezza lunghezza del tracciato.
     * @param nGiri numero di giri per vincere la gara.
     */
    public static void setTracciato(int lunghezza, int nGiri) {
        Tracciato.isSet = true;
        Tracciato.mapAuto = new HashMap<>();
        Tracciato.mapThread = new HashMap<>();
        Tracciato.vincitore = null;
        Tracciato.classifica = new ArrayList<>();
        
        Tracciato.lunghezza = lunghezza;
        Tracciato.nGiri = nGiri;
    }

    public static Pilota getVincitore() {
        return vincitore;
    }

    public static int getLunghezza() {
        return lunghezza;
    }

    public static int getnGiri() {
        return nGiri;
    }
    
    
}
