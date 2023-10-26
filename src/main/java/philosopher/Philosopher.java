package philosopher;
import java.util.concurrent.locks.ReentrantLock;

import static philosopher.DiningPhilosophers.NUM_EATING_ITERATIONS;

/**
 * Класс Philosopher является философом в проблеме обедающих философов.
 * У каждого философа есть свой уникальный идентификатор (id) и две вилки (левая и правая).
 *
 * @author Олег Тодор
 */
public class Philosopher extends Thread {
    private final int id;
    private final ReentrantLock leftFork;
    private final ReentrantLock rightFork;

    /**
     * Конструктор класса Philosopher.
     *
     * @param id         Уникальный идентификатор философа.
     * @param leftFork   Левая вилка философа.
     * @param rightFork  Правая вилка философа.
     */
    public Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    /**
     * Метод run() выполняет основную логику философа.
     * Философ повторяет фазы мышления и питания NUM_EATING_ITERATIONS раз.
     */
    public void run() {
        for (int i = 0; i < NUM_EATING_ITERATIONS; i++) {
            try {
                think();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            eat();
        }
    }

    /**
     * Приватный метод think() представляет фазу мышления философа.
     * Философ выводит сообщение о мышлении и может выполнять дополнительную логику мышления.
     */
    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " думает");

    }

    /**
     * Приватный метод eat() представляет фазу питания философа.
     * Философ захватывает левую и правую вилки, выводит сообщение о питании и может выполнять дополнительную логику питания.
     * После завершения питания, вилки освобождаются.
     */
    private void eat() {
        leftFork.lock();
        rightFork.lock();

        try {
            System.out.println("Философ " + id + " ест");
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rightFork.unlock();
            leftFork.unlock();
        }
    }
}