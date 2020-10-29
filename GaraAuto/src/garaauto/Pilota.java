package garaauto;

public class Pilota {

    private String nome;

    private int distPercorsa;

    private Automobile auto;

    private int giriFatti;

    public Pilota(String nome) {

    }

    public void addDistPercorsa(int dist) {
        int giri = 0;

        this.distPercorsa += dist;
        
        this.giriFatti = Math.floorDiv(Tracciato.lunghezza, this.distPercorsa);
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

}
