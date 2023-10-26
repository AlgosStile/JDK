package reference_book;


/**
 * Представляет информацию о сотруднике.
 */
public record Employee(int employeeId, String phoneNumber, String name, int experience) {
    /**
     * Создает новый экземпляр класса Employee с заданными параметрами.
     *
     * @param employeeId  табельный номер сотрудника
     * @param phoneNumber номер телефона сотрудника
     * @param name        имя сотрудника
     * @param experience  стаж работы сотрудника в годах
     */
    public Employee {
    }

    /**
     * Возвращает табельный номер сотрудника.
     *
     * @return табельный номер сотрудника
     */
    @Override
    public int employeeId() {
        return employeeId;
    }

    /**
     * Возвращает номер телефона сотрудника.
     *
     * @return номер телефона сотрудника
     */
    @Override
    public String phoneNumber() {
        return phoneNumber;
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return имя сотрудника
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Возвращает стаж работы сотрудника в годах.
     *
     * @return стаж работы сотрудника в годах
     */
    @Override
    public int experience() {
        return experience;
    }
}