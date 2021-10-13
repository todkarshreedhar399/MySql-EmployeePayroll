import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeePayRoll {
    public static void main(String[] args) {
        EmployeePayRollService employeePayRollService = new EmployeePayRollService();
        Scanner scanner = new Scanner(System.in);

        final int EXIT = 10;
        int choice = 0;
        while (choice != EXIT) {
            System.out.println("""
                    enter your choice
                    1. Execute query
                    2. update basic pay
                    3. display employee roll
                    4. select range of employee
                    5. calculate
                    6. add employee
                    10. EXIT
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {

                    employeePayRollService.fetchData();
                    employeePayRollService.display();
                }
                case 2 -> {
                    System.out.println("enter employee name");
                    String empName = scanner.next();
                    System.out.println("enter basic pay you want to update");
                    double basicPay = scanner.nextDouble();
                    employeePayRollService.updateBasicPay(empName, basicPay);
                }
                case 3 -> employeePayRollService.display();
                case 4 -> {
                    System.out.println("enter initial date");
                    LocalDate iDate = LocalDate.parse(scanner.next());
                    System.out.println("enter final date");
                    LocalDate eDate = LocalDate.parse(scanner.next());
                    employeePayRollService.selectEmployee(iDate,eDate);
                }
                case 5-> employeePayRollService.calculate();
                case 6-> {
                    System.out.println("Enter employee name");
                    String empName = scanner.next();
                    System.out.println("Enter phone Number");
                    String phoneNumber = scanner.next();
                    System.out.println("Enter address");
                    String address = scanner.next();
                    System.out.println("Enter employee department");
                    String department = scanner.next();
                    System.out.println("Enter Gender");
                    String gender = scanner.next();
                    System.out.println("Enter Employee BasicPay");
                    double basicPay = scanner.nextDouble();
                    System.out.println("Enter Employee Deductions");
                    double deductions = scanner.nextDouble();
                    System.out.println("Enter Employee TaxablePay");
                    double taxablePay = scanner.nextDouble();
                    System.out.println("Enter Employee IncomeTax");
                    double incomeTax = scanner.nextDouble();
                    System.out.println("Enter Employee NetPay");
                    double netPay = scanner.nextDouble();
                    System.out.println("Enter Employee Start date");
                    LocalDate empStart = LocalDate.parse(scanner.next());
                    Date eStart = Date.valueOf(empStart);
                    employeePayRollService.addEmployee(empName, phoneNumber, address, department, gender, basicPay, deductions, taxablePay, incomeTax, netPay,empStart));
                }
                case EXIT -> System.out.println("good bye");
            }
        }
    }
}
