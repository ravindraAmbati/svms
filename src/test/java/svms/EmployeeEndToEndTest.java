package svms;

import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.Entity.DBLog;
import svms.Entity.Employee;
import svms.Repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

@Ignore
public class EmployeeEndToEndTest {

    private static Logger logger = Logger.getLogger(EmployeeEndToEndTest.class);

    private static EmployeeRepository employeeRepository = null;

    private static String currentTimeStamp = null;

    @BeforeClass
    public static void dataSetup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        employeeRepository = (EmployeeRepository) context.getBean("employeeRepo");
        currentTimeStamp = Utility.getCurrrentTimeStamp();
        createDataForEmployee();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("dataSetup(): ");
    }

    @Test
    public void findAllEmployeesTest() {
        List<Employee> employees = employeeRepository.findAllEmployees();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("findAllEmployeesTest(): " + employees);
        Assert.assertEquals(6, employees.size());
    }

    @Test
    public void findEmployeeByIdTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Employee employee = new Employee.Builder().employeeId(999998L).personId(999998L).classId(999998L).role("teacher").salary(25000L).status("I").dBLog(dBLog).build();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("findPersonByIdTest(): expected: " + employee.toString() + " actual: " + employeeRepository.findEmployeeById(999998L).toString());
        Assert.assertEquals(employee.toString(), employeeRepository.findEmployeeById(999998L).toString());
    }

    @Test
    public void findEmployeeByStatusTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        Employee employee7 = new Employee.Builder().employeeId(999997L).personId(999997L).classId(999997L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        Employee employee6 = new Employee.Builder().employeeId(999996L).personId(999996L).classId(999996L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        Employee employee5 = new Employee.Builder().employeeId(999995L).personId(999995L).classId(999995L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        List<Employee> activeEmployees = Arrays.asList(employee7, employee6, employee5);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("findEmployeeByStatusTest(): expected: 3" + " actual: " + employeeRepository.findEmployeeByStatus("I").size());
        logger.info("findEmployeeByStatusTest(): expected: " + activeEmployees.toString() + " actual: " + employeeRepository.findEmployeeByStatus("A").toString());
        Assert.assertEquals(3, employeeRepository.findEmployeeByStatus("I").size());
        Assert.assertEquals(activeEmployees.toString(), employeeRepository.findEmployeeByStatus("A").toString());
    }

    @Test
    public void findEmployeeByRoleTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999994)).build();
        Employee employee4 = new Employee.Builder().employeeId(999994L).personId(999994L).classId(999994L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999993)).build();
        Employee employee3 = new Employee.Builder().employeeId(999993L).personId(999993L).classId(999993L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999992)).build();
        Employee employee2 = new Employee.Builder().employeeId(999992L).personId(999992L).classId(999992L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        List<Employee> activeEmployees = Arrays.asList(employee4, employee3, employee2);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("findEmployeeByRoleTest(): expected: 3" + " actual: " + employeeRepository.findEmployeeByRole("incharge").size());
        logger.info("findEmployeeByRoleTest(): expected: " + activeEmployees.toString() + " actual: " + employeeRepository.findEmployeeByRole("incharge").toString());
        Assert.assertEquals(3, employeeRepository.findEmployeeByRole("incharge").size());
        Assert.assertEquals(activeEmployees.toString(), employeeRepository.findEmployeeByRole("incharge").toString());
    }

    @Test
    public void saveEmployeeTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999991)).build();
        Employee employee = new Employee.Builder().employeeId(999991L).personId(999991L).classId(999991L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("saveEmployeeTest(): expected: " + employee.toString() + " actual: " + employeeRepository.findEmployeeById(999991L).toString());
        Assert.assertEquals(employee.toString(), employeeRepository.findEmployeeById(999991L).toString());
        Assert.assertEquals(1, employeeRepository.deleteEmployee(999991L));
    }

    @Test
    public void deleteEmployeeTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999990)).build();
        Employee employee = new Employee.Builder().employeeId(999990L).personId(999990L).classId(999990L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("deleteEmployeeTest()");
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        Assert.assertEquals(1, employeeRepository.deleteEmployee(999990L));
    }

    @Test
    public void updateEmployeeStatusTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999989)).build();
        Employee employee = new Employee.Builder().employeeId(999989L).personId(999989L).classId(999989L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        String expectedStatus = "A";
        employeeRepository.updateEmployeeStatus(999989L, expectedStatus);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("updateEmployeeStatusTest(): expected: " + expectedStatus + " actual: " + employeeRepository.findEmployeeById(999989L).getStatus());
        Assert.assertEquals(expectedStatus, employeeRepository.findEmployeeById(999989L).getStatus());
        Assert.assertEquals(1, employeeRepository.deleteEmployee(999989L));
    }

    @Test
    public void updateEmployeeRoleTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999988)).build();
        Employee employee = new Employee.Builder().employeeId(999988L).personId(999988L).classId(999988L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        String expectedRole = "Vice Principal";
        employeeRepository.updateEmployeeRole(999988L, expectedRole);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("updateEmployeeStatusTest(): expected: " + expectedRole + " actual: " + employeeRepository.findEmployeeById(999988L).getRole());
        Assert.assertEquals(expectedRole, employeeRepository.findEmployeeById(999988L).getRole());
        Assert.assertEquals(1, employeeRepository.deleteEmployee(999988L));
    }

    @Test
    public void updateEmployeeSalaryTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999987)).build();
        Employee employee = new Employee.Builder().employeeId(999987L).personId(999987L).classId(999987L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        Long expectedSalary = 30000L;
        employeeRepository.updateEmployeeSalary(999987L, expectedSalary);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("updateEmployeeStatusTest(): expected: " + expectedSalary + " actual: " + employeeRepository.findEmployeeById(999989L).getSalary());
        Assert.assertEquals(expectedSalary, employeeRepository.findEmployeeById(999987L).getSalary());
        Assert.assertEquals(1, employeeRepository.deleteEmployee(999987L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForEmployee();
        employeeRepository = null;
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("tearDown()");
    }

    private static void createDataForEmployee() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("### started createDataForEmployee() ###");
        employeeRepository.cleanUp();
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        Employee employee = new Employee.Builder().employeeId(999997L).personId(999997L).classId(999997L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        employee = new Employee.Builder().employeeId(999996L).personId(999996L).classId(999996L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        employee = new Employee.Builder().employeeId(999995L).personId(999995L).classId(999995L).role("teacher").salary(25000L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999994)).build();
        employee = new Employee.Builder().employeeId(999994L).personId(999994L).classId(999994L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999993)).build();
        employee = new Employee.Builder().employeeId(999993L).personId(999993L).classId(999993L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999992)).build();
        employee = new Employee.Builder().employeeId(999992L).personId(999992L).classId(999992L).role("incharge").salary(25000L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, employeeRepository.saveEmployee(employee));
        logger.info("createDataForEmployee(): data" + employeeRepository.findAllEmployees());
        logger.info("### ended createDataForEmployee() ###");
    }

    private static void cleanupDataForEmployee() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + EmployeeEndToEndTest.class.getName() + ": ");
        logger.info("### started cleanupDataForEmployee() ###");
        logger.info("cleanupDataForEmployee(): data before cleanup: " + employeeRepository.findAllEmployees());
        employeeRepository.cleanUp();
        logger.info("cleanupDataForEmployee(): data after cleanup: " + employeeRepository.findAllEmployees());
        logger.info("### ended cleanupDataForEmployee() ###");

    }
}

