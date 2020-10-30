package garaauto;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Auto extends Thread implements Automobile {

    private String scuderia;

    private Pilota pilota;

    private int vMin;

    private int vMax;

    public Auto(String scuderia, Pilota pilota, int vMin, int vMax, int priorita) {
        super(scuderia);
        setPriority(priorita);
        this.scuderia = scuderia;
        this.pilota = pilota;
        this.vMin = vMin;
        this.vMax = vMax;
    }

    @Override
    public void run() {
        while(true){
            try {
                int distanza = (int) (Math.random() * (vMax-vMin) + vMin);
                System.out.println("Distanza percorsa da" + this.scuderia +" : " + distanza + "mt");
                this.pilota.addDistPercorsa(distanza);
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getScuderia() {
        return scuderia;
    }

    @Override
    public Pilota getPilota() {
        return pilota;
    }

    @Override
    public int getVMin() {
        return vMin;
    }

    @Override
    public int getVMax() {
        return vMax;
    }

    @Override
    public void setScuderia(String scuderia) {
        this.scuderia = scuderia;
    }

    @Override
    public void setPilota(Pilota pilota) {
        this.pilota = pilota;
    }

    @Override
    public void setVMin(int vMin) {
        this.vMin = vMin;
    }

    @Override
    public void setVMax(int vMax) {
        this.vMax = vMax;
    }
    
}
    

    