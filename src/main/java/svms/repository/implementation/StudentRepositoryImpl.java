package svms.repository.implementation;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Utility;
import svms.entity.Student;
import svms.repository.StudentRepository;
import svms.repository.rowmapper.StudentRowMapper;
import svms.repository.statements.StudentStatements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    static Logger logger = Logger.getLogger(LoginUserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StudentRepositoryImpl() {
    }

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudentRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
    public List<Student> findAllStudents() {
        logger.info(getLoggerInfo() + "findAllStudents(): query: " + StudentStatements.FIND_ALL);
        return jdbcTemplate.query(StudentStatements.FIND_ALL, new StudentRowMapper());

    }

    @Override
    public Student findStudentById(Long studentId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("studentid", studentId);
        logger.info(getLoggerInfo() + "findStudentById(): query: " + StudentStatements.FIND_BY_ID);
        return namedParameterJdbcTemplate.queryForObject(StudentStatements.FIND_BY_ID, paramMap, new StudentRowMapper());
    }

    @Override
    public List<Student> findStudentByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(getLoggerInfo() + "findStudentByStatus(): query: " + StudentStatements.FIND_BY_STATUS);
        return namedParameterJdbcTemplate.query(StudentStatements.FIND_BY_STATUS, paramMap, new StudentRowMapper());
    }

    @Override
    public int saveStudent(Student student) {
        logger.info(getLoggerInfo() + "saveStudent(): query: " + StudentStatements.SAVE);
        return jdbcTemplate.update(StudentStatements.SAVE, student.getStudentId(), student.getPersonId(), student.getClassId(),
                student.getFee(), student.getStatus(), student.getDBLog().getTimestamp(), student.getDBLog().getApplication(),
                student.getDBLog().getMachineName(), student.getDBLog().getUserId());
    }

    @Override
    public int deleteStudent(Long studentId) {
        logger.info(getLoggerInfo() + "deleteStudent(): query: " + StudentStatements.DELETE);
        return jdbcTemplate.update(StudentStatements.DELETE, studentId);
    }

    @Override
    public int updateStudentStatus(Long studentId, String status) {
        logger.info(getLoggerInfo() + "updateStudentStatus(): query: " + StudentStatements.UPDATE_STATUS);
        return jdbcTemplate.update(StudentStatements.UPDATE_STATUS, status, studentId);
    }

    @Override
    public int updateStudentFee(Long studentId, Long fee) {
        logger.info(getLoggerInfo() + "updateStudentFee(): query: " + StudentStatements.UPDATE_FEE);
        return jdbcTemplate.update(StudentStatements.UPDATE_FEE, fee, studentId);
    }

    @Override
    public int cleanUp() {
        logger.info(getLoggerInfo() + "cleanUp(): query: " + StudentStatements.CLEAN_UP);
        return jdbcTemplate.update(StudentStatements.CLEAN_UP);
    }

    private String getLoggerInfo() {
        return Utility.getCurrrentTimeStamp() + ": " + StudentRepositoryImpl.class.getName() + ": ";
    }
}
