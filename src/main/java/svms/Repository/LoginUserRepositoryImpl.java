package svms.Repository;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Entity.LoginUser;
import svms.Repository.RowMapper.LoginUserRowMapper;
import svms.Repository.Statements.LoginUserStatements;
import svms.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginUserRepositoryImpl implements LoginUserRepository {

    static Logger logger = Logger.getLogger(LoginUserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepositoryImpl() {
    }

    public LoginUserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public LoginUserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public LoginUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LoginUser> findAllLoginUsers() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("findAllLoginUsers(): query: " + LoginUserStatements.FIND_ALL);
        return jdbcTemplate.query(LoginUserStatements.FIND_ALL, new LoginUserRowMapper());
    }

    @Override
    public LoginUser findLoginUserById(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("findLoginUserById(): query: " + LoginUserStatements.FIND_BY_ID + " and params: " + paramMap);
        return namedParameterJdbcTemplate.queryForObject(LoginUserStatements.FIND_BY_ID, paramMap, new LoginUserRowMapper());
    }

    @Override
    public LoginUser findLoginUserByUsername(String username) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("findLoginUserByUsername(): query: " + LoginUserStatements.FIND_BY_USERNAME + " and params: " + paramMap);
        return namedParameterJdbcTemplate.queryForObject(LoginUserStatements.FIND_BY_USERNAME, paramMap, new LoginUserRowMapper());
    }

    @Override
    public List<LoginUser> findLoginUserByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("findLoginUserByStatus(): query: " + LoginUserStatements.FIND_BY_STATUS + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(LoginUserStatements.FIND_BY_STATUS, paramMap, new LoginUserRowMapper());
    }

    @Override
    public List<LoginUser> findLoginUserByLastAttemptStatus(String lastAttemptStatus) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lastattemptstatus", lastAttemptStatus);
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("findLoginUserByLastAttemptStatus(): query: " + LoginUserStatements.FIND_BY_LAST_ATTEMPT_STATUS + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(LoginUserStatements.FIND_BY_LAST_ATTEMPT_STATUS, paramMap, new LoginUserRowMapper());
    }

    @Override
    public int saveLoginUser(LoginUser loginUser) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("saveLoginUser(): query: " + LoginUserStatements.SAVE + " and loginUser: " + loginUser);
        return jdbcTemplate.update(LoginUserStatements.SAVE, loginUser.getId(), loginUser.getUsername(), loginUser.getPassword(), loginUser.getStatus(), loginUser.getLoginTime(), loginUser.getLogoffTime(), loginUser.getLastAttemptStatus(), loginUser.getDBLog().getTimestamp(), loginUser.getDBLog().getApplication(), loginUser.getDBLog().getMachineName(), loginUser.getDBLog().getUserId());
    }

    @Override
    public int deleteLoginUser(Long id) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("deleteLoginUser(): query: " + LoginUserStatements.DELETE + " and id: " + id);
        return jdbcTemplate.update(LoginUserStatements.DELETE, id);
    }

    @Override
    public int updateLoginUserPassword(Long id, String password) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("updateLoginUserPassword(): query: " + LoginUserStatements.UPDATE_PASSWORD + " and id: " + id);
        return jdbcTemplate.update(LoginUserStatements.UPDATE_PASSWORD, password, id);
    }

    @Override
    public int updateLoginUserStatus(Long id, String status) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("updateLoginUserStatus(): query: " + LoginUserStatements.UPDATE_STATUS + " and id: " + id);
        return jdbcTemplate.update(LoginUserStatements.UPDATE_STATUS, status, id);
    }

    @Override
    public int cleanUp() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+LoginUserRepositoryImpl.class.getName()+": ");
        logger.info("cleanUp(): query: " + LoginUserStatements.CLEAN_UP);
        return jdbcTemplate.update(LoginUserStatements.CLEAN_UP);
    }

}
