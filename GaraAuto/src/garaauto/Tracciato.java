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
    private final int lunghezza;

    /**
     * Numero di giri totali da compiere per vincere la gara.
     */
    private final int nGiri;

    /**
     * HashMap che contiene tutti i Thread che sono le automobili in corsa.
     */
    private final HashMap<String, Thread> mapThread;
    
    /**
     * HashMap che contiene tutte le auto e tutti i loro dati.
     */
    private final HashMap<String, Automobile> mapAuto;

    /**
     * Tempo di inizio della gara.
     */
    private int inizioGara;

    /**
     * Pilota vincitore della gara.
     */
    private Pilota vincitore;
    
    /**
     * ArrayList dei piloti ordinati secondo l'arrivo.
     */
    private final ArrayList<Pilota> classifica;
    
    /**
     * Numero di auto totali che stanno sul tracciato.
     */
    private int nAuto;

    /**
     * Metodo che prepara il tracciato.
     * @param lunghezza lunghezza del tracciato.
     * @param nGiri numero di giri per vincere la gara.
     */
    public Tracciato(int lunghezza, int nGiri) {
        this.mapAuto = new HashMap<>();
        this.mapThread = new HashMap<>();
        this.vincitore = null;
        this.classifica = new ArrayList<>();
        
        this.lunghezza = lunghezza;
        this.nGiri = nGiri;
    }
    
    /**
     * Metodo che da l'inizio alla gara e attente un tot di tempo per
     * l'incidente.
     */
    public void iniziaGara() {
        int tempoIncidente;
        int tMax = 20;
        int tMin = 10;
        
        if(mapAuto.isEmpty()){
            System.out.println("Auto non aggiunte!");
            return;
        }
        
        for (String key : this.mapThread.keySet()) {
            mapThread.get(key).start();
        }

        this.inizioGara = (int) System.currentTimeMillis();

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

    public boolean addAuto(Automobile auto) {
        this.mapAuto.put(auto.getScuderia(), auto);
        
        if(auto instanceof Auto){
            this.mapThread.put(auto.getScuderia(), (Thread) auto);
        }else if(auto instanceof AutoSostituto){
            this.mapThread.put(auto.getScuderia(), new Thread(auto, auto.getScuderia()));
        }
        
		if(auto instanceof Auto && !(auto instanceof SafetyCar)){
			nAuto = mapAuto.size();
		}
        
        return true;
    }

    /**
     * Metodo che segna il vincitore e mette tutti i piloti che 
     * finiscono in una classifica.
     * @param pilota pilota che ha finito la gara.
     */
    public synchronized void fineGara(Pilota pilota) {
        if(this.vincitore == null){
            this.vincitore = pilota;
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
    private  void incidente() {
        Random generatore = new Random();
        
        Object[] array = mapAuto.keySet().toArray();
                
        String key = (String) array[generatore.nextInt(array.length)];
        
        Automobile auto = (Automobile) mapAuto.get(key);
        
        AutoSostituto autoS = new AutoSostituto("Scuderia sostitutiva", auto.getPilota(), 25, 50, this);
        
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
        
        addAuto(new SafetyCar("Safety Car", new Pilota("Pilota safety car"), 50, 55, this));
        
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

    public Pilota getVincitore() {
        return vincitore;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getnGiri() {
        return nGiri;
    }
    
    
}
