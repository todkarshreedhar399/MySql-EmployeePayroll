import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
    public ArrayList<Employee> empList;
    PreparedStatement preparedStatement;
    Connection connection = getConfig();

    public List<Employee> fetchData() {
        empList = new ArrayList<>();
        String query = "SELECT * from employee_payroll";
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setEmpId(resultSet.getInt("EmpId"));
                employee.setEmpName(resultSet.getString("EmpName"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setDepartment(resultSet.getString("Department"));
                employee.setEmpStart(resultSet.getString("EmpStart"));
                employee.setBasicPay(resultSet.getDouble("BasicPay"));
                employee.setGender(resultSet.getString("Gender"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
                employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
                employee.setNetPay(resultSet.getDouble("NetPay"));

                empList.add(employee);
            }
        } catch (SQLException e) {
            throw new EmployeeException("invalid column label");
        }
        return empList;
    }

    public void display() {
        for (Employee i : empList) {
            System.out.println(i.toString());
        }
    }

    public double updateBasicPay(String empName, double basicPay) {
        String UPDATE = "UPDATE employee_payroll SET BasicPay = ? WHERE EmpName = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setDouble(1, basicPay);
            preparedStatement.setString(2, empName);
            preparedStatement.executeUpdate();
            System.out.println("update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fetchData();
        for (Employee employee : empList) {
            if (employee.getEmpName().equals(empName)) {
                return employee.getBasicPay();
            }
        }
        return 0.0;
    }

    public void selectEmployee(LocalDate start, LocalDate end) {
        ArrayList<Employee> empSelected = new ArrayList<>();
        String select = "SELECT * FROM employee_payroll WHERE EmpStart BETWEEN ? AND ?";
        String sDate = String.valueOf(start);
        String eDate = String.valueOf(end);
        try {
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, sDate);
            preparedStatement.setString(2, eDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();

                employee.setEmpId(resultSet.getInt("EmpId"));
                employee.setEmpName(resultSet.getString("EmpName"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setDepartment(resultSet.getString("Department"));
                employee.setEmpStart(resultSet.getString("EmpStart"));
                employee.setBasicPay(resultSet.getDouble("BasicPay"));
                employee.setGender(resultSet.getString("Gender"));
                employee.setDeductions(resultSet.getDouble("Deductions"));
                employee.setTaxablePay(resultSet.getDouble("TaxablePay"));
                employee.setIncomeTax(resultSet.getDouble("IncomeTax"));
                employee.setNetPay(resultSet.getDouble("NetPay"));

                empSelected.add(employee);
            }
            for (Employee employee : empSelected) {
                System.out.println(employee);
            }
        } catch (SQLException e) {
            throw new EmployeeException("Invalid date");
        }
    }

    public void calculate() {
        Scanner scanner = new Scanner(System.in);

        final int EXIT = 6;
        int choice = 0;
        while (choice != EXIT) {
            System.out.println("""
                    Select\s
                    1. SUM
                    2. AVERAGE
                    3. MINIMUM
                    4. MAXIMUM
                    5. COUNT
                    6. EXIT
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> calculateQuery("SELECT Gender, SUM(BasicPay) FROM employee_payroll GROUP BY Gender");
                case 2 -> calculateQuery("SELECT Gender, AVG(BasicPay) FROM employee_payroll GROUP BY Gender");
                case 3 -> calculateQuery("SELECT Gender, MIN(BasicPay) FROM employee_payroll GROUP BY Gender");
                case 4 -> calculateQuery("SELECT Gender, MAX(BasicPay) FROM employee_payroll GROUP BY Gender");
                case 5 -> calculateQuery("SELECT gender, COUNT(EmpName) FROM employee_payroll GROUP BY Gender");
            }
        }
    }

    public void calculateQuery(String calculate) {
        List<Employee> result = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(calculate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setGender(resultSet.getString(1));
                employee.setBasicPay(resultSet.getDouble(2));

                result.add(employee);
            }
            if (calculate.contains("COUNT")) {
                for (Employee i : result) {
                    System.out.println("Gender: " + i.getGender() + " COUNT: " + i.getBasicPay());
                }
            } else {
                for (Employee i : result) {
                    System.out.println("Gender: " + i.getGender() + " Basic pay: " + i.getBasicPay());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String empName,String phoneNumber, String address,String department,String gender,
            double basicPay,double deductions,double taxablePay,double incomeTax,double netPay,
                                    LocalDate empStart) {
        empList = new ArrayList<>();

        String query = "INSERT INTO employee_payroll(EmpName, Department, Gender, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay, EmpStart, PhoneNumber, Address)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, empName);
            preparedStatement.setString(2, department);
            preparedStatement.setString(3,gender);
            preparedStatement.setDouble(4, basicPay);
            preparedStatement.setDouble(5,deductions);
            preparedStatement.setDouble(6, taxablePay);
            preparedStatement.setDouble(7, incomeTax);
            preparedStatement.setDouble(8, netPay);
            preparedStatement.setDate(9, Date.valueOf(empStart));
            preparedStatement.setString(10,phoneNumber);
            preparedStatement.setString(11,address);
            preparedStatement.executeUpdate();
            fetchData();
            display();
        } catch (SQLException e) {
            throw new EmployeeException("invalid column label");
        }
    }
}
