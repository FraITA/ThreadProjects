package garaauto;

public class Auto extends Thread implements Automobile {

    private String scuderia;

    private Pilota pilota;

    private int vMin;

    private int vMax;

    private int priorita;

    public Auto(String scuderia, Pilota pilota, int vMin, int vMax, int priorita) {
    }

    public Auto(String scuderia) {
    }

    @Override
    public void run() {
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
    public int getPriorita() {
        return priorita;
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
    public void setPriorita(int priorita) {
        this.priorita = priorita;
    }
    
}
    

    