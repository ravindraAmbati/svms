package svms.Repository;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Entity.Class;
import svms.Repository.RowMapper.AddressRowMapper;
import svms.Repository.RowMapper.ClassRowMapper;
import svms.Repository.Statements.AddressStatements;
import svms.Repository.Statements.ClassStatements;
import svms.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRepositoryImpl implements ClassRepository {
    static Logger logger = Logger.getLogger(AddressRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClassRepositoryImpl() {
    }

    public ClassRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ClassRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public ClassRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
    public List<Class> findAllClasses() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("findAllClasses(): query: " + ClassStatements.FIND_ALL);
        return jdbcTemplate.query(ClassStatements.FIND_ALL, new ClassRowMapper());

    }

    @Override
    public Class findClassById(Long classId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("classid", classId);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("findClassById(): query: " + ClassStatements.FIND_BY_ID);
        return namedParameterJdbcTemplate.queryForObject(ClassStatements.FIND_BY_ID, paramMap, new ClassRowMapper());

    }

    @Override
    public List<Class> findClassesByEmployeeId(Long employeeId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeid", employeeId);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("findClassesByEmployeeId(): query: " + ClassStatements.FIND_BY_EMPLOYEE_ID);
        return namedParameterJdbcTemplate.query(ClassStatements.FIND_BY_EMPLOYEE_ID, paramMap, new ClassRowMapper());
    }

    @Override
    public List<Class> findClassesByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("findClassesByStatus(): query: " + ClassStatements.FIND_BY_STATUS);
        return namedParameterJdbcTemplate.query(ClassStatements.FIND_BY_STATUS, paramMap, new ClassRowMapper());

    }

    @Override
    public int saveClass(Class aClass) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("saveClass(): query: " + ClassStatements.SAVE + " and Class: " + aClass);
        return jdbcTemplate.update(ClassStatements.SAVE, aClass.getClassId(), aClass.getName(),
                aClass.getNoOfBoys(), aClass.getNoOfGirls(), aClass.getEmployeeId(), aClass.getStatus(),
                aClass.getDBLog().getTimestamp(), aClass.getDBLog().getApplication(), aClass.getDBLog().getMachineName(),
                aClass.getDBLog().getUserId());

    }

    @Override
    public int deleteClass(Long classId) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ");
        logger.info("deleteClass(): query: " + ClassStatements.DELETE + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.DELETE, classId);

    }

    @Override
    public int updateClassStatus(Long classId, String status) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+ClassRepositoryImpl.class.getName()+": ");
        logger.info("updateClassStatus(): query: " + ClassStatements.UPDATE_STATUS + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.UPDATE_STATUS, status, classId);

    }

    @Override
    public int updateEmployeeId(Long classId, String employeeId) {
        logger.info(Utility.getCurrrentTimeStamp()+": "+ClassRepositoryImpl.class.getName()+": ");
        logger.info("updateEmployeeId(): query: " + ClassStatements.UPDATE_EMPLOYEE_ID + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.UPDATE_EMPLOYEE_ID, employeeId, classId);

    }

    @Override
    public int cleanUp() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+ClassRepositoryImpl.class.getName()+": ");
        logger.info("cleanUp(): query: " + ClassStatements.CLEAN_UP);
        return jdbcTemplate.update(ClassStatements.CLEAN_UP);

    }
}
