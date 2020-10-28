public class Main {
    public static void main(String[] args) {
        Platos plato = new Platos();
        Fregador fregador = new Fregador(plato);
        Organizador organizador = new Organizador(plato);
        Secador secador = new Secador(plato);
        Runnable target;
        Thread thread1 = new Thread(fregador,"Fregador");
        Thread thread2 = new Thread(secador,"Secador");
        Thread thread3 = new Thread(organizador,"Organizador");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        System.out.println("Cumpleaños feliz!");
    }
}
