package garaauto;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tracciato {

    protected static int lunghezza;

    protected static int nGiri;

    protected static HashMap<String, Thread> mapThread;
    
    protected static HashMap<String, Automobile> mapAuto;

    protected static boolean garaFinita;

    private static int inizioGara;

    protected static Pilota vincitore;
    
    private static boolean isSet = false;

    public static void iniziaGara() {
        if(!isSet){
            System.out.println("tracciato non settato!");
            return;
        }
        int tempoIncidente;
        int tMax = 60;
        int tMin = 10;
        
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

        incidente();

    }

    public static boolean addAuto(Automobile auto) {
        Tracciato.mapAuto.put(auto.getScuderia(), auto);
        
        if(auto instanceof Auto){
            Tracciato.mapThread.put(auto.getScuderia(), (Thread) auto);
        }else if(auto instanceof AutoSostituto){
            Tracciato.mapThread.put(auto.getScuderia(), new Thread(auto));
        }
        
        return true;
    }

    public static void vittoria(Pilota pilota) {
    }

    private static void incidente() {
        Random generatore = new Random();
        
        Object[] array = mapAuto.keySet().toArray();
                
        String key = (String) array[generatore.nextInt(array.length)];
        
        Automobile auto = (Automobile) mapAuto.get(key);
        
        AutoSostituto autoS = new AutoSostituto("Scuderia sostituta", auto.getPilota(), 50, 100);
        
        System.out.println("\n" + auto.getPilota().getNome() + " ha subito un incidente!\n");
        
        auto.getPilota().setAuto(autoS);
        
        addAuto(autoS);
        
        mapAuto.remove(auto.getScuderia());
        mapThread.remove(auto.getScuderia());
        
        mapThread.get(autoS.getScuderia()).start();
        
        for(String k : mapAuto.keySet()){
            mapThread.get(k).setPriority(2);
            mapAuto.get(k).setVMin(25);
            mapAuto.get(k).setVMax(50);
        }
        
        addAuto(new SafetyCar("Safety Car", new Pilota("Pilota safety car"), 50, 75));
        
        
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

    public static void fineGara() {
    }

    public static void setTracciato(int lunghezza, int nGiri) {
        Tracciato.isSet = true;
        Tracciato.mapAuto = new HashMap<>();
        Tracciato.mapThread = new HashMap<>();
        
        Tracciato.lunghezza = lunghezza;
        Tracciato.nGiri = nGiri;
    }
}
