import java.util.ArrayList;

public class Employee {
    int empId;
    String empName, phoneNumber, address, department, empStart, gender;
    double basicPay, deductions, taxablePay, incomeTax, netPay;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmpStart() {
        return empStart;
    }

    public void setEmpStart(String empStart) {
        this.empStart = empStart;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getTaxablePay() {
        return taxablePay;
    }

    public void setTaxablePay(double taxablePay) {
        this.taxablePay = taxablePay;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "model.Employee{" +
                "EmpId=" + empId +
                ", EmpName='" + empName + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                ", Address='" + address + '\'' +
                ", Department='" + department + '\'' +
                ", EmpStart='" + empStart + '\'' +
                ", Gender='" + gender + '\'' +
                ", BasicPay=" + basicPay +
                ", Deductions=" + deductions +
                ", TaxablePay=" + taxablePay +
                ", IncomeTax=" + incomeTax +
                ", NetPay=" + netPay +
                '}';
    }
}
