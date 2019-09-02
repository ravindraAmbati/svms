package svms.Repository;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Entity.Address;
import svms.Repository.RowMapper.AddressRowMapper;
import svms.Repository.Statements.AddressStatements;
import svms.Repository.Statements.LoginUserStatements;
import svms.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressRepositoryImpl implements AddressRepository {

    static Logger logger = Logger.getLogger(AddressRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    AddressRepositoryImpl() {

    }

    AddressRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    AddressRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    AddressRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Address> findAllAddress() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + AddressRepositoryImpl.class.getName() + ": ");
        logger.info("findAllAddress(): query: " + AddressStatements.FIND_ALL);
        return jdbcTemplate.query(AddressStatements.FIND_ALL, new AddressRowMapper());
    }

    @Override
    public Address findAddressById(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("addressid", id);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + AddressRepositoryImpl.class.getName() + ": ");
        logger.info("findAddressById(): query: " + AddressStatements.FIND_BY_ID);
        return namedParameterJdbcTemplate.queryForObject(AddressStatements.FIND_BY_ID, paramMap, new AddressRowMapper());
    }

    @Override
    public List<Address> findAddressByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + AddressRepositoryImpl.class.getName() + ": ");
        logger.info("findAddressByStatus(): query: " + AddressStatements.FIND_BY_STATUS + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(AddressStatements.FIND_BY_STATUS, paramMap, new AddressRowMapper());
    }

    @Override
    public int saveAddress(Address address) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + AddressRepositoryImpl.class.getName() + ": ");
        logger.info("saveAddress(): query: " + AddressStatements.SAVE + " and address: " + address);
        return jdbcTemplate.update(AddressStatements.SAVE, address.getAddressId(), address.getHouseNo(),
                address.getStreet(), address.getVillageTownCity(), address.getLandmark(), address.getDistrict(),
                address.getState(), address.getPincode(), address.getStatus(), address.getDBLog().getTimestamp(),
                address.getDBLog().getApplication(), address.getDBLog().getMachineName(), address.getDBLog().getUserId());
    }

    @Override
    public int deleteAddress(Long id) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + AddressRepositoryImpl.class.getName() + ": ");
        logger.info("deleteAddress(): query: " + AddressStatements.SAVE + " and id: " + id);
        return jdbcTemplate.update(AddressStatements.DELETE, id);
    }

    @Override
    public int updateAddressStatus(Long id, String status) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+AddressRepositoryImpl.class.getName()+": ");
        logger.info("updateAddressStatus(): query: " + AddressStatements.UPDATE_STATUS + " and id: " + id);
        return jdbcTemplate.update(AddressStatements.UPDATE_STATUS, status, id);
    }

    @Override
    public int cleanUp() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+AddressRepositoryImpl.class.getName()+": ");
        logger.info("cleanUp(): query: " + AddressStatements.CLEAN_UP);
        return jdbcTemplate.update(AddressStatements.CLEAN_UP);
    }
}
