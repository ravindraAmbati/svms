package svms.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import svms.Entity.Address;
import svms.Entity.Class;
import svms.Entity.DBLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassRowMapper implements RowMapper<Class> {
    @Override
    public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dbLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        Class aclass = new Class.Builder()
                .classId(rs.getLong("classid"))
                .name(rs.getString("name"))
                .noOfBoys(rs.getInt("no_of_boys"))
                .noOfGirls(rs.getInt("no_of_girls"))
                .employeeId(rs.getLong("employeeid"))
                .status(rs.getString("status"))
                .dBLog(dbLog)
                .build();
        return aclass;
    }
}
