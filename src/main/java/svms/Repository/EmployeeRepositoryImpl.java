package svms.Repository;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Entity.Employee;
import svms.Repository.RowMapper.EmployeeRowMapper;
import svms.Repository.Statements.EmployeeStatements;
import svms.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    static Logger logger = Logger.getLogger(LoginUserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeRepositoryImpl() {
    }

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EmployeeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> findAllEmployees() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("findAllEmployees(): query: " + EmployeeStatements.FIND_ALL);
        return jdbcTemplate.query(EmployeeStatements.FIND_ALL, new EmployeeRowMapper());
    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeid", employeeId);
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("findEmployeeById(): query: " + EmployeeStatements.FIND_BY_ID + " and params: " + paramMap);
        return namedParameterJdbcTemplate.queryForObject(EmployeeStatements.FIND_BY_ID, paramMap, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> findEmployeeByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("findEmployeeByStatus(): query: " + EmployeeStatements.FIND_BY_STATUS + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(EmployeeStatements.FIND_BY_STATUS, paramMap, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> findEmployeeByRole(String role) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("role", role);
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("findEmployeeByStatus(): query: " + EmployeeStatements.FIND_BY_ROLE + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(EmployeeStatements.FIND_BY_ROLE, paramMap, new EmployeeRowMapper());
    }

    @Override
    public int saveEmployee(Employee employee) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("saveEmployee(): query: " + EmployeeStatements.SAVE + " and employee: " + employee);
        return jdbcTemplate.update(EmployeeStatements.SAVE, employee.getEmployeeId(), employee.getPersonId(), employee.getClassId(),
                employee.getRole(), employee.getSalary(), employee.getStatus(), employee.getDBLog().getTimestamp(), employee.getDBLog().getApplication(),
                employee.getDBLog().getMachineName(), employee.getDBLog().getUserId());
    }

    @Override
    public int deleteEmployee(Long employeeId) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("deleteEmployee(): query: " + EmployeeStatements.DELETE + " and employeeId: " + employeeId);
        return jdbcTemplate.update(EmployeeStatements.DELETE, employeeId);
    }

    @Override
    public int updateEmployeeStatus(Long employeeId, String status) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("updateEmployeeStatus(): query: " + EmployeeStatements.UPDATE_STATUS + " and employeeId: " + employeeId);
        return jdbcTemplate.update(EmployeeStatements.UPDATE_STATUS, status, employeeId);
    }

    @Override
    public int updateEmployeeRole(Long employeeId, String role) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("updateEmployeeRole(): query: " + EmployeeStatements.UPDATE_ROLE + " and employeeId: " + employeeId);
        return jdbcTemplate.update(EmployeeStatements.UPDATE_ROLE, role, employeeId);
    }

    @Override
    public int updateEmployeeSalary(Long employeeId, Long salary) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("updateEmployeeSalary(): query: " + EmployeeStatements.UPDATE_SALARY + " and employeeId: " + employeeId);
        return jdbcTemplate.update(EmployeeStatements.UPDATE_SALARY, salary, employeeId);
    }

    @Override
    public int cleanUp() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+EmployeeRepositoryImpl.class.getName()+": ");
        logger.info("cleanUp(): query: " + EmployeeStatements.CLEAN_UP);
        return jdbcTemplate.update(EmployeeStatements.CLEAN_UP);
    }
}
