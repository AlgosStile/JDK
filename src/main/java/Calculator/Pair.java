package Calculator;

public record Pair<T1, T2>(T1 first, T2 second) {

    /**
     * Получает значение первого элемента коллекции.
     *
     * @return значение первого элемента
     *
     * first() и toString() по идее тут не нужны, если использую record Pair, но я оставил
     * для демонстрации).
     */
    @Override
    public T1 first() {
        return first;
    }


    /**
     * Преобразует объект в строковое представление.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}