package svms.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import svms.Entity.DBLog;
import svms.Entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dbLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        Person person = new Person.Builder()
                .personId(rs.getLong("personid"))
                .lastName(rs.getString("last_name"))
                .firstName(rs.getString("first_name"))
                .gender(rs.getString("gender"))
                .dateOfBirth(rs.getString("date_of_birth"))
                .dateOfJoin(rs.getString("date_of_join"))
                .contact(rs.getLong("contact"))
                .alternativeContact(rs.getLong("alternative_contact"))
                .addressId(rs.getLong("adressid"))
                .status(rs.getString("status"))
                .dBLog(dbLog)
                .build();
        return person;
    }
}
