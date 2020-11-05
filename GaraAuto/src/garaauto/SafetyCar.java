package garaauto;

public class SafetyCar extends Auto {

    public SafetyCar(String scuderia, Pilota pilota, int vMin, int vMax) {
        super(scuderia, pilota, vMin, vMax);
    }

    @Override
    public void run() {

        for (int i = 0; i < 30; ++i) {
            try {
                int distanza = (int) (Math.random() * (vMax - vMin) + vMin);
                this.pilota.addDistPercorsa(distanza);
                System.out.println("Distanza percorsa da " + this.scuderia + " : " + this.pilota.getDistPercorsa() + "mt");
                sleep(1000);
                i++;
            } catch (InterruptedException ex) {
                System.out.println("Thread interrotto");
            }
        }
    }
}
