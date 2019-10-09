package svms;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.entity.DBLog;
import svms.entity.Student;
import svms.repository.StudentRepository;

import java.util.Arrays;
import java.util.List;


public class StudentEndToEndTest {

    private static Logger logger = Logger.getLogger(StudentEndToEndTest.class);

    private static StudentRepository studentRepository = null;

    private static String currentTimeStamp = null;

    @BeforeClass
    public static void dataSetup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        studentRepository = (StudentRepository) context.getBean("studentRepo");
        currentTimeStamp = Utility.getCurrrentTimeStamp();
        createDataForStudent();
        logger.info(getLoggerInfo() + "dataSetup(): ");
    }

    @Test
    public void findAllStudentsTest() {
        List<Student> students = studentRepository.findAllStudents();
        logger.info(getLoggerInfo() + "findAllStudentsTest(): " + students);
        Assert.assertEquals(4, students.size());
    }

    @Test
    public void findStudentByIdTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Student student = new Student.Builder().studentId(999998L).personId(999998L).classId(999998L).fee(500L).status("I").dBLog(dBLog).build();
        logger.info(getLoggerInfo() + "findStudentByIdTest(): expected: " + student.toString() + " actual: " + studentRepository.findStudentById(999998L).toString());
        Assert.assertEquals(student.toString(), studentRepository.findStudentById(999998L).toString());
    }

    @Test
    public void findStudentByStatusTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        Student student7 = new Student.Builder().studentId(999997L).personId(999997L).classId(999997L).fee(500L).status("A").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        Student student6 = new Student.Builder().studentId(999996L).personId(999996L).classId(999996L).fee(500L).status("A").dBLog(dBLog).build();
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        Student student5 = new Student.Builder().studentId(999995L).personId(999995L).classId(999995L).fee(500L).status("A").dBLog(dBLog).build();
        List<Student> activeStudents = Arrays.asList(student7, student6, student5);
        logger.info(getLoggerInfo() + "findStudentByStatusTest(): expected: 3" + " actual: " + studentRepository.findStudentByStatus("A").size());
        logger.info(getLoggerInfo() + "findStudentByStatusTest(): expected: " + activeStudents.toString() + " actual: " + studentRepository.findStudentByStatus("A").toString());
        Assert.assertEquals(3, studentRepository.findStudentByStatus("A").size());
        Assert.assertEquals(activeStudents.toString(), studentRepository.findStudentByStatus("A").toString());
    }

    @Test
    public void saveStudentTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999991)).build();
        Student student = new Student.Builder().studentId(999991L).personId(999991L).classId(999991L).fee(200L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        logger.info(getLoggerInfo() + "saveStudentTest(): expected: " + student.toString() + " actual: " + studentRepository.findStudentById(999991L).toString());
        Assert.assertEquals(student.toString(), studentRepository.findStudentById(999991L).toString());
        Assert.assertEquals(1, studentRepository.deleteStudent(999991L));
    }

    @Test
    public void deleteStudentTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999990)).build();
        Student student = new Student.Builder().studentId(999990L).personId(999990L).classId(999990L).fee(200L).status("I").dBLog(dBLog).build();
        logger.info(getLoggerInfo() + "deleteStudentTest()");
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        Assert.assertEquals(1, studentRepository.deleteStudent(999990L));
    }

    @Test
    public void updateStudentStatusTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999993)).build();
        Student student = new Student.Builder().studentId(999993L).personId(999993L).classId(999993L).fee(200L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        String expectedStatus = "A";
        studentRepository.updateStudentStatus(999993L, expectedStatus);
        logger.info(getLoggerInfo() + "updateStudentStatusTest(): expected: " + expectedStatus + " actual: " + studentRepository.findStudentById(999993L).getStatus());
        Assert.assertEquals(expectedStatus, studentRepository.findStudentById(999993L).getStatus());
        Assert.assertEquals(1, studentRepository.deleteStudent(999993L));
    }

    @Test
    public void updateEmployeeSalaryTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999992)).build();
        Student student = new Student.Builder().studentId(999992L).personId(999992L).classId(999992L).fee(200L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        Long expectedFee = 300L;
        studentRepository.updateStudentFee(999992L, expectedFee);
        logger.info(getLoggerInfo() + "updateEmployeeStatusTest(): expected: " + expectedFee + " actual: " + studentRepository.findStudentById(999992L).getFee());
        Assert.assertEquals(expectedFee, studentRepository.findStudentById(999992L).getFee());
        Assert.assertEquals(1, studentRepository.deleteStudent(999992L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForStudent();
        studentRepository = null;
        logger.info(getLoggerInfo() + "tearDown()");
    }

    private static void createDataForStudent() {
        logger.info(getLoggerInfo() + "### started createDataForStudent() ###");
        studentRepository.cleanUp();
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Student student = new Student.Builder().studentId(999998L).personId(999998L).classId(999998L).fee(500L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        student = new Student.Builder().studentId(999997L).personId(999997L).classId(999997L).fee(500L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        student = new Student.Builder().studentId(999996L).personId(999996L).classId(999996L).fee(500L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        student = new Student.Builder().studentId(999995L).personId(999995L).classId(999995L).fee(500L).status("A").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        logger.info(getLoggerInfo() + "createDataForStudent(): data" + studentRepository.findAllStudents());
        logger.info(getLoggerInfo() + "### ended createDataForStudent() ###");
    }

    private static void cleanupDataForStudent() {
        logger.info(getLoggerInfo() + "### started cleanupDataForStudent() ###");
        logger.info(getLoggerInfo() + "cleanupDataForStudent(): data before cleanup: " + studentRepository.findAllStudents());
        studentRepository.cleanUp();
        logger.info(getLoggerInfo() + "cleanupDataForStudent(): data after cleanup: " + studentRepository.findAllStudents());
        logger.info(getLoggerInfo() + "### ended cleanupDataForStudent() ###");

    }

    private static String getLoggerInfo() {
        return Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ";
    }
}

