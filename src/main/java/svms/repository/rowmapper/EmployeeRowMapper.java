package svms.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import svms.entity.DBLog;
import svms.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dBLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        Employee employee = new Employee.Builder()
                .employeeId(rs.getLong("employeeid"))
                .personId(rs.getLong("personid"))
                .classId(rs.getLong("classid"))
                .role(rs.getString("role"))
                .salary(rs.getLong("salary"))
                .status(rs.getString("status"))
                .dBLog(dBLog)
                .build();
        return employee;
    }
}
