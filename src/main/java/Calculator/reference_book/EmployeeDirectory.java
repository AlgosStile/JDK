package Calculator.reference_book;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс `EmployeeDirectory` представляет собой каталог сотрудников.
 * Этот класс позволяет добавлять сотрудников, а также выполнять поиск сотрудников по различным критериям.
 */
public class EmployeeDirectory {
    private final List<Employee> employees;

    /**
     * Создает новый экземпляр класса `EmployeeDirectory`.
     * Инициализирует список сотрудников пустым списком.
     */
    public EmployeeDirectory() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Возвращает список сотрудников с заданным стажем.
     *
     * @param experience требуемый стаж сотрудников
     * @return список сотрудников с заданным стажем
     */
    public List<Employee> findEmployeesByExperience(int experience) {
        List<Employee> matchingEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.experience() == experience) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }

    /**
     * Возвращает список номеров телефонов сотрудников с заданным именем.
     *
     * @param name требуемое имя сотрудника
     * @return список номеров телефонов сотрудников с заданным именем
     */
    public List<String> findPhoneNumbersByName(String name) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.name().equalsIgnoreCase(name)) {
                phoneNumbers.add(employee.phoneNumber());
            }
        }
        return phoneNumbers;
    }

    /**
     * Возвращает сотрудника по заданному табельному номеру.
     *
     * @param employeeId табельный номер сотрудника
     * @return сотрудник с заданным табельным номером, или null, если сотрудник не найден
     */
    public Employee findEmployeeByEmployeeId(int employeeId) {
        for (Employee employee : employees) {
            if (employee.employeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }
}