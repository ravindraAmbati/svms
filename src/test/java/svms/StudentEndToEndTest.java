package svms;

import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.Entity.DBLog;
import svms.Entity.Student;
import svms.Repository.StudentRepository;

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
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("dataSetup(): ");
    }

    @Test
    public void findAllStudentsTest() {
        List<Student> students = studentRepository.findAllStudents();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("findAllStudentsTest(): " + students);
        Assert.assertEquals(4, students.size());
    }

    @Test
    public void findStudentByIdTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Student student = new Student.Builder().studentId(999998L).personId(999998L).classId(999998L).fee(500L).status("I").dBLog(dBLog).build();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("findStudentByIdTest(): expected: " + student.toString() + " actual: " + studentRepository.findStudentById(999998L).toString());
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
        List<Student> activeStudents = Arrays.asList(student5, student6, student7);
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("findStudentByStatusTest(): expected: 3" + " actual: " + studentRepository.findStudentByStatus("A").size());
        logger.info("findStudentByStatusTest(): expected: " + activeStudents.toString() + " actual: " + studentRepository.findStudentByStatus("A").toString());
        Assert.assertEquals(3, studentRepository.findStudentByStatus("A").size());
        Assert.assertEquals(activeStudents.toString(), studentRepository.findStudentByStatus("A").toString());
    }

    @Test
    public void saveStudentTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999991)).build();
        Student student = new Student.Builder().studentId(999991L).personId(999991L).classId(999991L).fee(200L).status("I").dBLog(dBLog).build();
        Assert.assertEquals(1, studentRepository.saveStudent(student));
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("saveStudentTest(): expected: " + student.toString() + " actual: " + studentRepository.findStudentById(999991L).toString());
        Assert.assertEquals(student.toString(), studentRepository.findStudentById(999991L).toString());
        Assert.assertEquals(1, studentRepository.deleteStudent(999991L));
    }

    @Test
    public void deleteStudentTest() {
        DBLog dBLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999990)).build();
        Student student = new Student.Builder().studentId(999990L).personId(999990L).classId(999990L).fee(200L).status("I").dBLog(dBLog).build();
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("deleteStudentTest()");
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
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("updateStudentStatusTest(): expected: " + expectedStatus + " actual: " + studentRepository.findStudentById(999993L).getStatus());
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
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("updateEmployeeStatusTest(): expected: " + expectedFee + " actual: " + studentRepository.findStudentById(999992L).getFee());
        Assert.assertEquals(expectedFee, studentRepository.findStudentById(999992L).getFee());
        Assert.assertEquals(1, studentRepository.deleteStudent(999992L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForStudent();
        studentRepository = null;
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("tearDown()");
    }

    private static void createDataForStudent() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("### started createDataForStudent() ###");
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
        logger.info("createDataForStudent(): data" + studentRepository.findAllStudents());
        logger.info("### ended createDataForStudent() ###");
    }

    private static void cleanupDataForStudent() {
        logger.info(Utility.getCurrrentTimeStamp() + ": " + StudentEndToEndTest.class.getName() + ": ");
        logger.info("### started cleanupDataForStudent() ###");
        logger.info("cleanupDataForStudent(): data before cleanup: " + studentRepository.findAllStudents());
        studentRepository.cleanUp();
        logger.info("cleanupDataForStudent(): data after cleanup: " + studentRepository.findAllStudents());
        logger.info("### ended cleanupDataForStudent() ###");

    }
}

