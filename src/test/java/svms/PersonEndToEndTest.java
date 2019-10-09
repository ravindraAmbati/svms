package svms;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.entity.DBLog;
import svms.entity.Person;
import svms.repository.PersonRepository;

import java.util.Arrays;
import java.util.List;


public class PersonEndToEndTest {

    private static Logger logger = Logger.getLogger(PersonEndToEndTest.class);

    private static PersonRepository personRepository = null;

    private static String currentTimeStamp = null;

    @BeforeClass
    public static void dataSetup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        personRepository = (PersonRepository) context.getBean("personRepo");
        currentTimeStamp = Utility.getCurrrentTimeStamp();
        createDataForPerson();
        logger.info(getLoggerInfo() + "dataSetup(): ");
    }

    @Test
    public void findAllPersonsTest() {
        List<Person> persons = personRepository.findAllPersons();
        logger.info(getLoggerInfo() + "findAllPersonsTest(): " + persons);
        Assert.assertEquals(4, persons.size());
    }

    @Test
    public void findPersonByIdTest() {
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person = new Person.Builder().personId(999998L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        logger.info(getLoggerInfo() + "findPersonByIdTest(): expected: " + person.toString() + " actual: " + personRepository.findPersonById(999998L).toString());
        Assert.assertEquals(person.toString(), personRepository.findPersonById(999998L).toString());
    }

    @Test
    public void findPersonByStatusTest() {
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person8 = new Person.Builder().personId(999998L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person7 = new Person.Builder().personId(999997L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person6 = new Person.Builder().personId(999996L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person5 = new Person.Builder().personId(999995L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        List<Person> activePersons = Arrays.asList(person8, person7, person6, person5);
        logger.info(getLoggerInfo() + "findPersonByStatusTest(): expected: 3" + " actual: " + personRepository.findPersonByStatus("A").size());
        logger.info(getLoggerInfo() + "findPersonByStatusTest(): expected: " + activePersons.toString() + " actual: " + personRepository.findPersonByStatus("A").toString());
        Assert.assertEquals(4, personRepository.findPersonByStatus("A").size());
        Assert.assertEquals(activePersons.toString(), personRepository.findPersonByStatus("A").toString());
    }

    @Test
    public void savePersonTest() {
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person = new Person.Builder().personId(999994L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        logger.info(getLoggerInfo() + "savePersonTest(): expected: " + person.toString() + " actual: " + personRepository.findPersonById(999994L).toString());
        Assert.assertEquals(person.toString(), personRepository.findPersonById(999994L).toString());
        Assert.assertEquals(1, personRepository.deletePerson(999994L));
    }

    @Test
    public void deletePersonTest() {
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person = new Person.Builder().personId(999993L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        logger.info(getLoggerInfo() + "deletePersonTest()");
        Assert.assertEquals(1, personRepository.savePerson(person));
        Assert.assertEquals(1, personRepository.deletePerson(999993L));
    }

    @Test
    public void updatePersonStatusTest() {
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person = new Person.Builder().personId(999992L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        String expectedStatus = "A";
        personRepository.updatePersonStatus(999992L, expectedStatus);
        logger.info(getLoggerInfo() + "updatePersonStatusTest(): expected: " + expectedStatus + " actual: " + personRepository.findPersonById(999992L).getStatus());
        Assert.assertEquals(expectedStatus, personRepository.findPersonById(999992L).getStatus());
        Assert.assertEquals(1, personRepository.deletePerson(999992L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForPerson();
        personRepository = null;
        logger.info(getLoggerInfo() + "tearDown()");
    }

    private static void createDataForPerson() {
        logger.info(getLoggerInfo() + "### started createDataForPerson() ###");
        personRepository.cleanUp();
        String dateOfBirth = Utility.getTimeStamp(1992, 07, 23);
        String dateOfJoin = Utility.getTimeStamp(2017, 06, 01);
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Person person = new Person.Builder().personId(999998L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        person = new Person.Builder().personId(999997L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        person = new Person.Builder().personId(999996L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        person = new Person.Builder().personId(999995L).firstName("ravindra").lastName("ambati").gender("M").dateOfBirth(dateOfBirth).dateOfJoin(dateOfJoin).contact(1234567890L).alternativeContact(1234567890L).status("A").addressId(999998L).dBLog(dBLog).build();
        Assert.assertEquals(1, personRepository.savePerson(person));
        logger.info(getLoggerInfo() + "createDataForPerson(): data" + personRepository.findAllPersons());
        logger.info(getLoggerInfo() + "### ended createDataForPerson() ###");
    }

    private static void cleanupDataForPerson() {
        logger.info(getLoggerInfo() + "### started cleanupDataForPerson() ###");
        logger.info(getLoggerInfo() + "cleanupDataForPerson(): data before cleanup: " + personRepository.findAllPersons());
        personRepository.cleanUp();
        logger.info(getLoggerInfo() + "cleanupDataForPerson(): data after cleanup: " + personRepository.findAllPersons());
        logger.info(getLoggerInfo() + "### ended cleanupDataForPerson() ###");

    }

    private static String getLoggerInfo() {
        return Utility.getCurrrentTimeStamp() + ": " + PersonEndToEndTest.class.getName() + ": ";
    }
}

