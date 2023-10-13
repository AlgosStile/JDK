package Calculator;

public class Main {

    /**
     * Точка входа программы.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        int sum = (int) Calculator.sum(5, 3);
        System.out.println("Сумма: " + sum);

        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 3};
        boolean arraysEqual = compareArrays(array1, array2);
        System.out.println("Массивы одинаковые? " + arraysEqual);

        Pair<String, Integer> pair = new Pair<>("Ключ", 10);
        System.out.println("Пара: " + pair.toString());
    }

    /**
     * Сравнивает два массива типа T и возвращает true, если они равны, и false в противном случае..
     *
     * @param array1 первый массив для сравнения
     * @param array2 второй массив для сравнения
     * @return true, если массивы равны, false в противном случае
     */
    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) return false;

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) return false;
        }

        return true;
    }
}
