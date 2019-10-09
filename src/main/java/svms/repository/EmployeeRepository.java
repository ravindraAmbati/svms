package svms.repository;

import svms.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long employeeId);

    List<Employee> findEmployeeByStatus(String status);

    List<Employee> findEmployeeByRole(String role);

    int saveEmployee(Employee employee);

    int deleteEmployee(Long employeeId);

    int updateEmployeeStatus(Long employeeId, String status);

    int updateEmployeeRole(Long employeeId, String role);

    int updateEmployeeSalary(Long employeeId, Long salary);

    int cleanUp();

}
