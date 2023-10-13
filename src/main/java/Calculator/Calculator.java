package Calculator;

/**
 * Вариант кода 1:
 * В этом случае, все значения приводятся к double перед выполнением вычислений, и возвращается результат типа double.
 */

public class Calculator {
    /**
     * Вычисляет сумму двух чисел.
     *
     * @param num1 первый номер
     * @param num2 второй номер
     * @return сумма чисел num1 и num2
     */
    public static <T extends Number, U extends Number> double sum(T num1, U num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number, U extends Number> double multiply(T num1, U num2) {
        return num1.doubleValue() * num2.doubleValue();
    }


    /**
     * Делит два числа и возвращает результат.
     *
     * @param num1 первое число, которое нужно разделить
     * @param num2 второе число, на которое нужно разделить
     * @return результат деления числа1 на число2
     */
    public static <T extends Number, U extends Number> double divide(T num1, U num2) {
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return num1.doubleValue() / num2.doubleValue();
    }


    /**
     * Вычисляет разницу между двумя числами.
     *
     * @param num1 первое число, которое нужно вычесть
     * @param num2 второе число, которое нужно вычесть из первого числа
     * @return разница между двумя числами
     */
    public static <T extends Number, U extends Number> double subtract(T num1, U num2) {
        return num1.doubleValue() - num2.doubleValue();
    }
}
/**
 * Вариант кода 2:
 * Ниже вариант через if - ы более громоздкий, но более точный(без плавающей имею в виду) и менее лаконичный.
 * Если будете проверять этот вариант, то нужно убрать в строке:
 * int sum = (int) Calculator.sum(5, 3); - тут убираем приведение к (int)
 */

//    public static <T extends Number> T sum(T num1, T num2) {
//        if (num1 instanceof Integer && num2 instanceof Integer)
//            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
//        if (num1 instanceof Double && num2 instanceof Double)
//            return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
//        throw new IllegalArgumentException("Unsupported number types");
//    }
//
//    public static <T extends Number> T multiply(T num1, T num2) {
//        if (num1 instanceof Integer && num2 instanceof Integer)
//            return (T) Integer.valueOf(num1.intValue() * num2.intValue());
//        if (num1 instanceof Double && num2 instanceof Double)
//            return (T) Double.valueOf(num1.doubleValue() * num2.doubleValue());
//        throw new IllegalArgumentException("Unsupported number types");
//    }
//
//    public static <T extends Number> T divide(T num1, T num2) {
//        if (num1 instanceof Integer && num2 instanceof Integer)
//            return (T) Integer.valueOf(num1.intValue() / num2.intValue());
//        if (num1 instanceof Double && num2 instanceof Double)
//            return (T) Double.valueOf(num1.doubleValue() / num2.doubleValue());
//        throw new IllegalArgumentException("Unsupported number types");
//    }
//
//    public static <T extends Number> T subtract(T num1, T num2) {
//        if (num1 instanceof Integer && num2 instanceof Integer)
//            return (T) Integer.valueOf(num1.intValue() - num2.intValue());
//        if (num1 instanceof Double && num2 instanceof Double)
//            return (T) Double.valueOf(num1.doubleValue() - num2.doubleValue());
//        throw new IllegalArgumentException("Unsupported number types");
//    }
//}