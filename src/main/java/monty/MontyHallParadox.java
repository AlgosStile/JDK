package monty;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Класс для демонстрации и оценки вероятности в парадоксе Монти Холла.
 * *****************
 * Цель программы - показать, выполнив большое количество симулированных игр,
 * что вероятность выигрыша при переключении дверей составляет 2/3, в то время как вероятность
 * выигрыша при удержании исходного выбора составляет только 1/3.
 */
public class MontyHallParadox {
    /**
     * Главный метод класса MontyHallParadox.
     * Запускает симуляцию игры Монти Холла для определенного количества тестов
     * и собирает статистику о количестве выигрышей и проигрышей при переключении и не переключении дверей.
     */
    public static void main(String[] args) {
        int numTests = 1000;
        Map<Integer, String> results = new HashMap<>();

        int winsWithoutSwitching = 0;
        int winsWithSwitching = 0;

        Random random = new Random();

        for (int i = 1; i <= numTests; i++) {
            int prizeDoor = random.nextInt(3) + 1;
            int chosenDoor = random.nextInt(3) + 1;

            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3) + 1;
            } while (revealedDoor == prizeDoor || revealedDoor == chosenDoor);

            int switchedDoor = 6 - chosenDoor - revealedDoor;

            boolean winWithoutSwitching = chosenDoor == prizeDoor;
            boolean winWithSwitching = switchedDoor == prizeDoor;

            if (winWithoutSwitching) {
                winsWithoutSwitching++;
                results.put(i, "Победа (без переключения)");
            } else if (winWithSwitching) {
                winsWithSwitching++;
                results.put(i, "Победа (с переключением)");
            } else {
                results.put(i, "Потеря");
            }
        }

        System.out.println("Полученные результаты:");
        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            System.out.println("Тест: " + entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("Победа (без переключения): " + winsWithoutSwitching);
        System.out.println("Победа (с переключением): " + winsWithSwitching);
        System.out.println("Потери: " + (numTests - (winsWithoutSwitching + winsWithSwitching)));
    }
}
