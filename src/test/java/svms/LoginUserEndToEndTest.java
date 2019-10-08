package svms;

import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.Entity.DBLog;
import svms.Entity.LoginUser;
import svms.Repository.LoginUserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoginUserEndToEndTest {

    private static Logger logger = Logger.getLogger(LoginUserEndToEndTest.class);

    private static LoginUserRepository loginUserRepository = null;

    private static String currentTimeStamp = null;

    @BeforeClass
    public static void dataSetup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        loginUserRepository = (LoginUserRepository) context.getBean("loginUserRepo");
        currentTimeStamp = Utility.getCurrrentTimeStamp();
        createDataForLoginUser();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("dataSetup(): ");
    }

    @Test
    public void findAllLoginUsersTest() {
        List<LoginUser> loginUsers = loginUserRepository.findAllLoginUsers();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("findAllLoginUsersTest(): " + loginUsers);
        Assert.assertEquals(9, loginUsers.size());
    }

    @Test
    public void findLoginUserByIdTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        LoginUser loginUser = new LoginUser.Builder().id(999998L).username("rambati999998").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("findLoginUserByIdTest(): expected: " + loginUser.toString()+" actual: "+ loginUserRepository.findLoginUserById(999998L).toString());
        Assert.assertEquals(loginUser.toString(), loginUserRepository.findLoginUserById(999998L).toString());
    }

    @Test
    public void findLoginUserByUsernameTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(999997))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(999997L).username("rambati999997").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("findLoginUserByUsernameTest(): expected: " + loginUser.toString()+" actual: "+ loginUserRepository.findLoginUserByUsername("rambati999997").toString());
        Assert.assertEquals(loginUser.toString(), loginUserRepository.findLoginUserByUsername("rambati999997").toString());
    }

    @Test
    public void findLoginUserByStatusTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(999995))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser5 = new LoginUser.Builder().id(999995L).username("rambati999995").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        dbLog = new DBLog.Builder().userId(String.valueOf(new Long(999993))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser3 = new LoginUser.Builder().id(999993L).username("rambati999993").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        dbLog = new DBLog.Builder().userId(String.valueOf(new Long(999992))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser2 = new LoginUser.Builder().id(999992L).username("rambati999992").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).lastAttemptStatus("F").dbLog(dbLog).build();
        List<LoginUser> activeLoginUsers = Arrays.asList(loginUser5, loginUser3, loginUser2);
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("findLoginUserByStatusTest(): expected: 5"+" actual: "+ loginUserRepository.findLoginUserByStatus("I").size());
        logger.info("findLoginUserByStatusTest(): expected: " + activeLoginUsers.toString()+" actual: "+ loginUserRepository.findLoginUserByStatus("A").toString());
        Assert.assertEquals(5, loginUserRepository.findLoginUserByStatus("I").size());
        Assert.assertEquals(activeLoginUsers.toString(), loginUserRepository.findLoginUserByStatus("A").toString());
    }

    @Test
    public void findLoginUserByLastAttemptStatusTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(999994))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser SLoginUser = new LoginUser.Builder().id(999994L).username("rambati999994").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).lastAttemptStatus("S").dbLog(dbLog).build();
        List<LoginUser> successLoginUsers = Collections.singletonList(SLoginUser);
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("findLoginUserByLastAttemptStatusTest(): expected: 1"+" actual: "+ loginUserRepository.findLoginUserByLastAttemptStatus("S").size());
        logger.info("findLoginUserByLastAttemptStatusTest(): expected: " + successLoginUsers.toString()+" actual: "+ loginUserRepository.findLoginUserByLastAttemptStatus("S").toString());
        Assert.assertEquals(1, loginUserRepository.findLoginUserByLastAttemptStatus("S").size());
        Assert.assertEquals(successLoginUsers.toString(), loginUserRepository.findLoginUserByLastAttemptStatus("S").toString());
    }

    @Test
    public void saveLoginUserTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(95103))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(95103L).username("rambati95103").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("saveLoginUserTest(): expected: " + loginUser.toString()+" actual: "+ loginUserRepository.findLoginUserById(95103L).toString());
        logger.info("saveLoginUserTest(): expected: " + loginUser.toString()+" actual: "+ loginUserRepository.findLoginUserByUsername("rambati95103").toString());
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        Assert.assertEquals(loginUser.toString(), loginUserRepository.findLoginUserById(95103L).toString());
        Assert.assertEquals(loginUser.toString(), loginUserRepository.findLoginUserByUsername("rambati95103").toString());
        Assert.assertEquals(1, loginUserRepository.deleteLoginUser(95103L));
    }

    @Test
    public void deleteLoginUserTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(1234))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(1234L).username("rambati01234").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("deleteLoginUserTest()");
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        Assert.assertEquals(1, loginUserRepository.deleteLoginUser(1234L));
    }

    @Test
    public void updateLoginUserPasswordTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(987654L))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(987654L).username("rambati987654").password("test").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        String expectedPassword = "updated_test";
        loginUserRepository.updateLoginUserPassword(987654L, expectedPassword);
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("updateLoginUserPasswordTest(): expected: " + expectedPassword+" actual: "+ loginUserRepository.findLoginUserById(987654L).getPassword().toString());
        Assert.assertEquals(expectedPassword, loginUserRepository.findLoginUserById(987654L).getPassword().toString());
        Assert.assertEquals(1, loginUserRepository.deleteLoginUser(987654L));
    }

    @Test
    public void updateLoginUserStatusTest() {
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(new Long(963852L))).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(963852L).username("rambati963852").password("test").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        String expectedStatus = "U";
        loginUserRepository.updateLoginUserStatus(963852L, expectedStatus);
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("updateLoginUserStatusTest(): expected: " + expectedStatus+" actual: "+ loginUserRepository.findLoginUserById(963852L).getStatus());
        Assert.assertEquals(expectedStatus, loginUserRepository.findLoginUserById(963852L).getStatus());
        Assert.assertEquals(1, loginUserRepository.deleteLoginUser(963852L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForLoginUser();
        loginUserRepository = null;
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("tearDown()");
    }

    private static void createDataForLoginUser() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("### started createDataForLoginUser() ###");
        loginUserRepository.cleanUp();
        DBLog dbLog = new DBLog.Builder().userId(String.valueOf(999998L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        LoginUser loginUser = new LoginUser.Builder().id(999998L).username("rambati999998").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999997L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999997L).username("rambati999997").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999996L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999996L).username("rambati999996").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999995L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999995L).username("rambati999995").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999994L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999994L).username("rambati999994").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).lastAttemptStatus("S").dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999993L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999993L).username("rambati999993").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999992L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999992L).username("rambati999992").password("test").status("A").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).lastAttemptStatus("F").dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999991L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999991L).username("rambati999991").password("test").status("I").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        dbLog = new DBLog.Builder().userId(String.valueOf(999990L)).machineName(Utility.getHostName()).application(Utility.getApplication()).timestamp(currentTimeStamp).build();
        loginUser = new LoginUser.Builder().id(999990L).username("rambati999990").password("test").loginTime(currentTimeStamp).logoffTime(currentTimeStamp).dbLog(dbLog).build();
        Assert.assertEquals(1, loginUserRepository.saveLoginUser(loginUser));
        logger.info("createDataForLoginUser(): data" + loginUserRepository.findAllLoginUsers());
        logger.info("### ended createDataForLoginUser() ###");
    }

    private static void cleanupDataForLoginUser() {
        logger.info(Utility.getCurrrentTimeStamp()+": "+ LoginUserEndToEndTest.class.getName()+": ");
        logger.info("### started cleanupDataForLoginUser() ###");
        logger.info("cleanupDataForLoginUser(): data before cleanup: " + loginUserRepository.findAllLoginUsers());
        loginUserRepository.cleanUp();
        logger.info("cleanupDataForLoginUser(): data after cleanup: " + loginUserRepository.findAllLoginUsers());
        logger.info("### ended cleanupDataForLoginUser() ###");

    }
}
