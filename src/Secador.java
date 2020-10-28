import java.util.Random;

public class Secador implements Runnable  {
    private final Platos plato;
    Random random = new Random();

    public Secador(Platos plato) {
        this.plato = plato;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                secar();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
    private void secar() throws InterruptedException {
        int platoId = plato.cogerMojado();
        int rnd = random.nextInt(3 + 1);
        Thread.sleep(rnd * 1000);
        System.out.printf("%s ha secado el plato #%d\n", Thread.currentThread().getName(), platoId);
        plato.ponerSeco(platoId);
    }
}
