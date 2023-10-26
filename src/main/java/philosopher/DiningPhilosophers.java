package philosopher;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс DiningPhilosophers представляет решение проблемы обедающих философов.
 * Философы(NUM_PHILOSOPHERS) сидят вокруг круглого стола и соревнуются за ресурсы (вилки), чтобы поесть.
 * В консоль выдается номер философа например № 0 ест, № 1 думает и т д.
 * Всего в итерациях задействовано 3 философа как в задании.
 *
 * @author Олег Тодор
 */
public class DiningPhilosophers {

    /**
     * Количество итераций, во время которых философы будут есть.
     */
    static int NUM_EATING_ITERATIONS = 3;

    /**
     * Главный метод, запускающий программу.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        int NUM_PHILOSOPHERS = 3;
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        ReentrantLock[] forks = new ReentrantLock[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);
            philosophers[i].start();
        }
    }
}