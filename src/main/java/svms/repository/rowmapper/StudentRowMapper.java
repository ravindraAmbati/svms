package svms.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import svms.entity.DBLog;
import svms.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dBLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        Student student = new Student.Builder()
                .studentId(rs.getLong("studentid"))
                .personId(rs.getLong("personid"))
                .classId(rs.getLong("classid"))
                .fee(rs.getLong("fee"))
                .status(rs.getString("status"))
                .dBLog(dBLog)
                .build();
        return student;
    }
}
