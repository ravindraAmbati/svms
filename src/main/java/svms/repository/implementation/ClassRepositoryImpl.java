package svms.repository.implementation;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Utility;
import svms.entity.Class;
import svms.repository.ClassRepository;
import svms.repository.rowmapper.ClassRowMapper;
import svms.repository.statements.ClassStatements;

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
        logger.info(getLoggerInfo() + "findAllClasses(): query: " + ClassStatements.FIND_ALL);
        return jdbcTemplate.query(ClassStatements.FIND_ALL, new ClassRowMapper());

    }

    @Override
    public Class findClassById(Long classId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("classid", classId);
        logger.info(getLoggerInfo() + "findClassById(): query: " + ClassStatements.FIND_BY_ID);
        return namedParameterJdbcTemplate.queryForObject(ClassStatements.FIND_BY_ID, paramMap, new ClassRowMapper());

    }

    @Override
    public List<Class> findClassesByEmployeeId(Long employeeId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeid", employeeId);
        logger.info(getLoggerInfo() + "findClassesByEmployeeId(): query: " + ClassStatements.FIND_BY_EMPLOYEE_ID);
        return namedParameterJdbcTemplate.query(ClassStatements.FIND_BY_EMPLOYEE_ID, paramMap, new ClassRowMapper());
    }

    @Override
    public List<Class> findClassesByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(getLoggerInfo() + "findClassesByStatus(): query: " + ClassStatements.FIND_BY_STATUS);
        return namedParameterJdbcTemplate.query(ClassStatements.FIND_BY_STATUS, paramMap, new ClassRowMapper());

    }

    @Override
    public int saveClass(Class aClass) {
        logger.info(getLoggerInfo() + "saveClass(): query: " + ClassStatements.SAVE + " and Class: " + aClass);
        return jdbcTemplate.update(ClassStatements.SAVE, aClass.getClassId(), aClass.getName(),
                aClass.getNoOfBoys(), aClass.getNoOfGirls(), aClass.getEmployeeId(), aClass.getStatus(),
                aClass.getDBLog().getTimestamp(), aClass.getDBLog().getApplication(), aClass.getDBLog().getMachineName(),
                aClass.getDBLog().getUserId());

    }

    @Override
    public int deleteClass(Long classId) {
        logger.info(getLoggerInfo() + "deleteClass(): query: " + ClassStatements.DELETE + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.DELETE, classId);

    }

    @Override
    public int updateClassStatus(Long classId, String status) {
        logger.info(getLoggerInfo() + "updateClassStatus(): query: " + ClassStatements.UPDATE_STATUS + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.UPDATE_STATUS, status, classId);

    }

    @Override
    public int updateEmployeeId(Long classId, String employeeId) {
        logger.info(getLoggerInfo() + "updateEmployeeId(): query: " + ClassStatements.UPDATE_EMPLOYEE_ID + " and classId: " + classId);
        return jdbcTemplate.update(ClassStatements.UPDATE_EMPLOYEE_ID, employeeId, classId);

    }

    @Override
    public int cleanUp() {
        logger.info(getLoggerInfo() + "cleanUp(): query: " + ClassStatements.CLEAN_UP);
        return jdbcTemplate.update(ClassStatements.CLEAN_UP);
    }

    private String getLoggerInfo() {
        return Utility.getCurrrentTimeStamp() + ": " + ClassRepositoryImpl.class.getName() + ": ";
    }
}
