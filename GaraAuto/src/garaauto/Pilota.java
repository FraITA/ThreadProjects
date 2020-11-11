package garaauto;

/**
 * Classe che simula un pilota.
 * @author user
 */
public class Pilota {

    /**
     * Nome del pilota.
     */
    private String nome;

    /**
     * Distanza totale percorsa dal pilota.
     */
    private int distPercorsa;

    /**
     * Auto che guida il pilota.
     */
    private Automobile auto;

    /**
     * Numero totale di giri fatti.
     */
    private int giriFatti;

    public Pilota(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che aggiunge la distanza percorsa al totale e conta i giri
     * fatti in totale al momento.
     * @param dist distanza percosa in un secondo.
     */
    public void addDistPercorsa(int dist) {
        this.distPercorsa += dist;
        
        this.giriFatti = Math.floorDiv(this.distPercorsa, Tracciato.getLunghezza());
    }

    public String getNome() {
        return nome;
    }

    public int getDistPercorsa() {
        return distPercorsa;
    }

    public Automobile getAuto() {
        return auto;
    }

    public int getGiriFatti() {
        return giriFatti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAuto(Automobile auto) {
        this.auto = auto;
    }

    public void setGiriFatti(int giriFatti) {
        this.giriFatti = giriFatti;
    }

    public void setDistPercorsa(int distPercorsa) {
        this.distPercorsa = distPercorsa;
    }

    
}
