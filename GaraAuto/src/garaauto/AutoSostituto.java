package garaauto;

/**
 * Classe che simula un'auto da usare come sostituta in caso di incidente.
 * @author user
 */
public class AutoSostituto implements Automobile {

    /**
     * Scuderia dell'auto.
     */
    private String scuderia;

    /**
     * Pilota che guida l'auto.
     */
    private Pilota pilota;

    /**
     * Numero di metri minimi che può percorrere al secondo.
     */
    private int vMin;

    /**
     * Numero di metri massimi che può percorrere al secondo.
     */
    private int vMax;
    
    /**
     * Variabile booleana che segna se l'auto è ancora in gara.
     */
    private boolean inGara;

    /**
     * Metodo che costruisce l'oggetto AutoSostituto
     * @param scuderia scuderia dell'auto
     * @param pilota pilota che guida l'auto
     * @param vMin velocità minima in mt/s
     * @param vMax velocità minima in mt/s
     */
    public AutoSostituto(String scuderia, Pilota pilota, int vMin, int vMax) {
        this.scuderia = scuderia;
        this.pilota = pilota;
        this.vMin = vMin;
        this.vMax = vMax;
        this.inGara = true;
    }

    /**
     * Metodo che permette la simulazione della corsa dell'auto
     */
    @Override
    public void run() {
        this.pilota.setAuto(this);
        while(this.inGara){
            try {
                int distanza = (int) (Math.random() * (vMax-vMin) + vMin);
                this.pilota.addDistPercorsa(distanza);
                System.out.println("Distanza percorsa da " + this.scuderia +" : " + this.pilota.getDistPercorsa() + "mt");
                
                if(Tracciato.getVincitore() != null){
                    System.out.println(this.pilota.getNome() + ": " + Tracciato.getVincitore().getNome() + " ha vinto!\n");
                }
                
                if(this.pilota.getGiriFatti() == Tracciato.getnGiri()){
                    Tracciato.fineGara(this.pilota);
                    if(Tracciato.getVincitore() == null){
                        System.out.println(this.pilota.getNome() + ": Ho vinto!\n");
                    }
                    this.inGara = false;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrotto");
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
    public boolean isInGara() {
        return inGara;
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

    @Override
    public void setInGara(boolean inGara) {
        this.inGara = inGara;
    }
    
    
}
