package People;
import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.ChronoUnit.*;

public class Employee implements EmployeeCalculations{
    private int id;
    private String name;
    private String streetAdress;
    private LocalDate hireDate;
    private int salary;
    private float bonusPercent = 10;

    private static int idCounter = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBonusPercent() {
        return bonusPercent;
    }

    public void setBonusPercent(float bonusPercent) {
        this.bonusPercent = bonusPercent;
    }

    public String getName() {
        return name;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee( String name, LocalDate hireDate,String streetAdress, int salary) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.hireDate = hireDate;
        this.streetAdress = streetAdress;
        this.salary = salary;
    }

    @Override
    public float calculateBonus() {
        return getSalary() * (getBonusPercent() / 100 + 1);
    }

    @Override
    public float yearlySalary() {
        return 10 * getSalary() + 2 * calculateBonus(); //Every employee gets a bonus for Easter and Christmas
    }

    @Override
    public void workingFor() {
        System.out.println(this.getName() + "works for: ");
        LocalDate localDate = LocalDate.now();
        long daysBetween = DAYS.between(hireDate, localDate);
        if (daysBetween < 365)
            if (daysBetween > 30)
                System.out.println(MONTHS.between(hireDate, localDate) + " months \n");
            else
                System.out.println(daysBetween + " days \n");
            else
            System.out.println(YEARS.between(hireDate, localDate) + " years \n");



    }
}
