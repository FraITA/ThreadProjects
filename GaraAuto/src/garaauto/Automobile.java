package garaauto;

/**
 * Interfaccia usata per riconoscere le classi che simulano auto e 
 * poterne usare i loro metodi comuni
 * @author user
 */
public interface Automobile extends Runnable{
    
    public String getScuderia();

    public void setScuderia(String scuderia);

    public Pilota getPilota();

    public void setPilota(Pilota pilota);

    public int getVMin();

    public void setVMin(int vMin);

    public int getVMax();

    public void setVMax(int vMax);
    
    public boolean isInGara();
    
    public void setInGara(boolean inGara);
}
