package svms.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import svms.Entity.Address;
import svms.Entity.DBLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        DBLog dbLog = new DBLog.Builder()
                .timestamp(rs.getString("timestamp"))
                .application(rs.getString("application"))
                .machineName(rs.getString("machinename"))
                .userId(rs.getString("userid"))
                .build();
        Address address = new Address.Builder()
                .addressId(rs.getLong("addressid"))
                .houseNo(rs.getString("houseno"))
                .landmark(rs.getString("landmark"))
                .street(rs.getString("street"))
                .villageTownCity(rs.getString("village_town_city"))
                .district(rs.getString("district"))
                .state(rs.getString("state"))
                .pincode(rs.getLong("pincode"))
                .status(rs.getString("status"))
                .dbLog(dbLog)
                .build();
        return address;
    }
}
