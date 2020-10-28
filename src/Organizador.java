import java.util.Random;

public class Organizador implements Runnable {
    private final Platos plato;
    Random random = new Random();

    public Organizador(Platos plato) {
        this.plato = plato;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                organizar();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void organizar() throws InterruptedException {
        int platoId = plato.cogerSeco();
        int rnd = random.nextInt(2 + 1);
        Thread.sleep(rnd * 1000);
        System.out.printf("%s ha organizado el plato #%d\n", Thread.currentThread().getName(), platoId);

        plato.organizar(platoId);
    }
}
