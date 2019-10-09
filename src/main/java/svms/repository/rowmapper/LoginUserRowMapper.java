package svms.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import svms.entity.DBLog;
import svms.entity.LoginUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserRowMapper implements RowMapper<LoginUser> {
    @Override
    public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dbLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        LoginUser loginUser = new LoginUser.Builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .status(rs.getString("status"))
                .loginTime(rs.getString("login_time"))
                .logoffTime(rs.getString("logoff_time"))
                .lastAttemptStatus(rs.getString("last_attempt_status"))
                .dbLog(dbLog)
                .build();
        return loginUser;
    }
}
