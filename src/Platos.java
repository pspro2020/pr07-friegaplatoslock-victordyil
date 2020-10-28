import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Calendar.*;
import static java.util.Calendar.SECOND;

public class Platos {
    private ArrayList<Integer> platosSucios = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    private ArrayList<Integer> platosMojados = new ArrayList<Integer>();
    private ArrayList<Integer> platosSecos = new ArrayList<Integer>();
    private ArrayList<Integer> alacena = new ArrayList<Integer>();
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition isEmpty = lock.newCondition();
    Calendar now = getInstance();

    public int cogerSucio() throws InterruptedException {
        Integer plato;
        lock.lock();
        try {

            while (platosSucios.isEmpty()) {
                isEmpty.await();
            }
            plato = platosSucios.remove(0);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: cogido el plato sucio #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
            isEmpty.signal();
            return plato;
        } finally {
            lock.unlock();
        }

    }

    public void ponerMojado(int plato) {
        lock.lock();
        try {
            platosMojados.add(plato);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: puesto el plato mojado #%d \n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int cogerSeco() throws InterruptedException {
        Integer plato;
        lock.lock();
        try {
            while (platosSecos.isEmpty()) {
                isEmpty.await();
            }
            plato = platosSecos.remove(0);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: cogido el plato seco #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
            isEmpty.signal();
            return plato;
        } finally {
            lock.unlock();
        }
    }

    public void organizar(int plato) {
        lock.lock();
        try {
            alacena.add(plato);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: puesto un plato #%d en la alacena\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int cogerMojado() throws InterruptedException {
        Integer plato;
        lock.lock();
        try {
            while (platosMojados.isEmpty()) {
                isEmpty.await();
            }
            plato = platosMojados.remove(0);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: cogido el plato mojado #%d\n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), plato);
            isEmpty.signal();
            return plato;
        } finally {
            lock.unlock();
        }
    }

    public void ponerSeco(int platoId) {
        lock.lock();
        try {
            platosSecos.add(platoId);
            now = getInstance();
            System.out.printf("%d:%d:%d - %s: puesto el plato seco #%d \n", now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), Thread.currentThread().getName(), platoId);
            isEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}
