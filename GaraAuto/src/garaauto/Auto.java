package garaauto;

public class Auto extends Thread implements Automobile {

    protected String scuderia;

    protected Pilota pilota;

    protected int vMin;

    protected int vMax;
    
    protected boolean inGara;

    public Auto(String scuderia, Pilota pilota, int vMin, int vMax) {
        super(scuderia);
        this.scuderia = scuderia;
        this.pilota = pilota;
        this.vMin = vMin;
        this.vMax = vMax;
        this.inGara = true;
    }

    @Override
    public void run() {
        while(true){
            try {
                int distanza = (int) (Math.random() * (vMax-vMin) + vMin);
                this.pilota.addDistPercorsa(distanza);
                System.out.println("Distanza percorsa da " + this.scuderia +" : " + this.pilota.getDistPercorsa() + "mt");
                sleep(1000);
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
    

    