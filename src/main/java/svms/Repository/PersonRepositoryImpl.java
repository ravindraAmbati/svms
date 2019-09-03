package svms.Repository;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import svms.Entity.Person;
import svms.Repository.RowMapper.PersonRowMapper;
import svms.Repository.Statements.PersonStatements;
import svms.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepositoryImpl implements PersonRepository {

    static Logger logger = Logger.getLogger(LoginUserRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PersonRepositoryImpl() {
    }

    public PersonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PersonRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public PersonRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
    public List<Person> findAllPersons() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("findAllPersons(): query: " + PersonStatements.FIND_ALL);
        return jdbcTemplate.query(PersonStatements.FIND_ALL, new PersonRowMapper());
    }

    @Override
    public Person findPersonById(Long personId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("personid", personId);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("findPersonById(): query: " + PersonStatements.FIND_BY_ID);
        return namedParameterJdbcTemplate.queryForObject(PersonStatements.FIND_BY_ID, paramMap, new PersonRowMapper());

    }

    @Override
    public Person findPersonByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("firstname", firstName);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("findPersonByFirstName(): query: " + PersonStatements.FIND_BY_FIRSTNAME);
        return namedParameterJdbcTemplate.queryForObject(PersonStatements.FIND_BY_FIRSTNAME, paramMap, new PersonRowMapper());
    }

    @Override
    public List<Person> findPersonByStatus(String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", status);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("findPersonByStatus(): query: " + PersonStatements.FIND_BY_STATUS + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(PersonStatements.FIND_BY_STATUS, paramMap, new PersonRowMapper());
    }

    @Override
    public List<Person> findPersonByGender(String gender) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gender", gender);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("findPersonByGender(): query: " + PersonStatements.FIND_BY_GENDER + " and params: " + paramMap);
        return namedParameterJdbcTemplate.query(PersonStatements.FIND_BY_GENDER, paramMap, new PersonRowMapper());

    }

    @Override
    public int savePerson(Person person) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("savePerson(): query: " + PersonStatements.SAVE + " and person: " + person);
        return jdbcTemplate.update(PersonStatements.SAVE, person.getPersonId(), person.getFirstName(), person.getLastName(),
                person.getGender(), person.getDateOfBirth(), person.getDateOfJoin(), person.getContact(), person.getAlternativeContact(),
                person.getAddressId(), person.getStatus(), person.getDBLog().getTimestamp(), person.getDBLog().getApplication(), person.getDBLog().getMachineName(), person.getDBLog().getUserId());

    }

    @Override
    public int deletePerson(Long personId) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("deletePerson(): query: " + PersonStatements.DELETE + " and personId: " + personId);
        return jdbcTemplate.update(PersonStatements.DELETE, personId);
    }

    @Override
    public int updatePersonStatus(Long personId, String status) {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("updatePersonStatus(): query: " + PersonStatements.UPDATE_STATUS + " and personId: " + personId);
        return jdbcTemplate.update(PersonStatements.UPDATE_STATUS, status, personId);

    }

    @Override
    public int cleanUp() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + PersonRepositoryImpl.class.getName() + ": ");
        logger.info("cleanUp(): query: " + PersonStatements.CLEAN_UP);
        return jdbcTemplate.update(PersonStatements.CLEAN_UP);

    }
}
