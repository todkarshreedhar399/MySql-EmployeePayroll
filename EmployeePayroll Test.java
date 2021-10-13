import org.junit.Assert;
import org.junit.Test;
import service.EmployeePayRollService;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayRollTest {
    EmployeePayRollService employeePayRollService = new EmployeePayRollService();

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        List<Employee> employeePayrollDataList = employeePayRollService.fetchData();
        Assert.assertEquals(10, employeePayrollDataList.size());
    }

    @Test
    public void givenUpdatingTerisaBasicPay_whenUpdate_ShouldReturnUpdatedPay() {
        double BasicPay = 3000000;
        String Name = "Terisa";
        double salaryUpdated = employeePayRollService.updateBasicPay(Name, BasicPay);
        Assert.assertEquals(BasicPay, salaryUpdated,0.0);
    }

    @Test
    public void givenUpdatingRahulBasicPay_whenUpdate_ShouldReturnUpdatedPay() {
        double BasicPay = 800000;
        String Name = "rahul";
        double salaryUpdated = employeePayRollService.updateBasicPay(Name, BasicPay);
        Assert.assertEquals(BasicPay, salaryUpdated,0.0);
    }

    @Test
    public void givenNewEmployee_WhenAdded_ShouldReturnEmployeeName() throws EmployeeException {
        employeePayRollService.addEmployee("Ankita","582471365","delhi","marketing","F",
                780000,2000,3000,1000,72000, LocalDate.ofEpochDay(2015-02-21));
        String eName = employeePayRollService.fetchData().get(employeePayRollService.empList.size()-1).getEmpName();
        Assert.assertEquals(eName,"Ankita");
    }

}
