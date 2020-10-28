import java.util.Random;

public class Fregador implements Runnable {
    Random random = new Random();
    private final Platos plato;

    public Fregador(Platos plato) {
        this.plato = plato;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                lavar();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void lavar() throws InterruptedException {
        int platoId = plato.cogerSucio();
        int rnd = random.nextInt(4 + 4);
        Thread.sleep(rnd * 1000);
        System.out.printf("%s ha fregado el plato #%d\n", Thread.currentThread().getName(), platoId);

        plato.ponerMojado(platoId);
    }
}
