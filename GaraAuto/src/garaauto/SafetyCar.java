package garaauto;

public class SafetyCar extends Auto {

    public SafetyCar(String scuderia, Pilota pilota, int vMin, int vMax, int priorita) {
        super(scuderia, pilota, vMin, vMax, priorita);
    }

    public SafetyCar(String scuderia) {
        super(scuderia);
    }

    @Override
    public void run() {
    }
}
