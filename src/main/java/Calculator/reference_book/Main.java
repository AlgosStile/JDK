package Calculator.reference_book;

import java.util.List;

/**
 * Главный класс приложения, содержащий метод `main` для демонстрации функциональности справочника сотрудников.
 */
public class Main {

    /**
     * Точка входа в приложение. Создает экземпляр `EmployeeDirectory`, добавляет сотрудников и выполняет поисковые операции.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        Employee employee1 = new Employee(1, "8800567890", "Олег", 5);
        Employee employee2 = new Employee(2, "8800543210", "Василий", 3);
        Employee employee3 = new Employee(3, "8800522555", "Константин", 7);
        Employee employee4 = new Employee(4, "8800533555", "Юлия", 8);
        Employee employee5 = new Employee(5, "8800544555", "Константин", 1);
        Employee employee6 = new Employee(6, "8800568555", "Юлия", 9);

        directory.addEmployee(employee1);
        directory.addEmployee(employee2);
        directory.addEmployee(employee3);
        directory.addEmployee(employee4);
        directory.addEmployee(employee5);
        directory.addEmployee(employee6);


        int requiredExperience = 5;
        List<Employee> employeesWithRequiredExperience = directory.findEmployeesByExperience(requiredExperience);
        System.out.println("Сотрудники со стажем " + requiredExperience + " года(лет):");
        for (Employee employee : employeesWithRequiredExperience) {
            System.out.println("Табельный номер: " + employee.employeeId() + ", Имя: " + employee.name());
        }


        String requiredName = "Мария";
        List<String> phoneNumbers = directory.findPhoneNumbersByName(requiredName);
        System.out.println("Номера телефонов сотрудника(-ов) по имени " + requiredName + ":");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }


        int requiredEmployeeId = 2;
        Employee foundEmployee = directory.findEmployeeByEmployeeId(requiredEmployeeId);
        if (foundEmployee != null) {
            System.out.println("Информация о сотруднике с табельным номером " + requiredEmployeeId + ":");
            System.out.println("Имя: " + foundEmployee.name());
            System.out.println("Номер телефона: " + foundEmployee.phoneNumber());
            System.out.println("Стаж: " + foundEmployee.experience() + " года(лет)");
        } else {
            System.out.println("Сотрудник с табельным номером " + requiredEmployeeId + " не найден");
        }
    }
}