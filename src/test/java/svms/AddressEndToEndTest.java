package svms;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import svms.entity.Address;
import svms.entity.DBLog;
import svms.repository.AddressRepository;

import java.util.Arrays;
import java.util.List;

public class AddressEndToEndTest {

    private static Logger logger = Logger.getLogger(AddressEndToEndTest.class);

    private static AddressRepository addressRepository = null;

    private static String currentTimeStamp = null;

    @BeforeClass
    public static void dataSetup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("testApplicationContext.xml");
        addressRepository = (AddressRepository) context.getBean("addressRepo");
        currentTimeStamp = Utility.getCurrrentTimeStamp();
        createDataForAddress();
        logger.info(getLoggerInfo() + "dataSetup(): ");
    }

    @Test
    public void findAllAddressTest() {
        List<Address> addresses = addressRepository.findAllAddress();
        logger.info(getLoggerInfo() + "findAllAddressTest(): " + addresses);
        Assert.assertEquals(4, addresses.size());
    }

    @Test
    public void findAddressByIdTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Address address = new Address.Builder().addressId(999998L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("A").dbLog(dbLog).build();
        logger.info(getLoggerInfo() + "findAddressByIdTest(): expected: " + address.toString() + " actual: " + addressRepository.findAddressById(999998L).toString());
        Assert.assertEquals(address.toString(), addressRepository.findAddressById(999998L).toString());
    }

    @Test
    public void findAddressByStatusTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        Address address7 = new Address.Builder().addressId(999997L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        Address address6 = new Address.Builder().addressId(999996L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        Address address5 = new Address.Builder().addressId(999995L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        List<Address> inActiveAddresses = Arrays.asList(address7, address6, address5);
        logger.info(getLoggerInfo() + "findAddressByStatusTest(): expected: 3" + " actual: " + addressRepository.findAddressByStatus("I").size());
        logger.info(getLoggerInfo() + "findAddressByStatusTest(): expected: " + inActiveAddresses.toString() + " actual: " + addressRepository.findAddressByStatus("I").toString());
        Assert.assertEquals(3, addressRepository.findAddressByStatus("I").size());
        Assert.assertEquals(inActiveAddresses.toString(), addressRepository.findAddressByStatus("I").toString());
    }

    @Test
    public void saveAddressTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999994)).build();
        Address address = new Address.Builder().addressId(999994L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        logger.info(getLoggerInfo() + "saveAddressTest(): expected: " + address.toString() + " actual: " + addressRepository.findAddressById(999994L).toString());
        Assert.assertEquals(address.toString(), addressRepository.findAddressById(999994L).toString());
        Assert.assertEquals(1, addressRepository.deleteAddress(999994L));
    }

    @Test
    public void deleteAddressTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999993)).build();
        Address address = new Address.Builder().addressId(999993L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        logger.info(getLoggerInfo() + "deleteAddressTest()");
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        Assert.assertEquals(1, addressRepository.deleteAddress(999993L));
    }

    @Test
    public void updateAddressStatusTest() {
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999992)).build();
        Address address = new Address.Builder().addressId(999992L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        String expectedStatus = "A";
        addressRepository.updateAddressStatus(999992L, expectedStatus);
        logger.info(getLoggerInfo() + "updateAddressStatusTest(): expected: " + expectedStatus + " actual: " + addressRepository.findAddressById(999992L).getStatus());
        Assert.assertEquals(expectedStatus, addressRepository.findAddressById(999992L).getStatus());
        Assert.assertEquals(1, addressRepository.deleteAddress(999992L));
    }

    @AfterClass
    public static void tearDown() {
        cleanupDataForAddress();
        addressRepository = null;
        logger.info(getLoggerInfo() + "tearDown()");
    }

    private static void createDataForAddress() {
        logger.info(getLoggerInfo() + "### started createDataForAddress() ###");
        addressRepository.cleanUp();
        DBLog dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999998)).build();
        Address address = new Address.Builder().addressId(999998L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("A").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999997)).build();
        address = new Address.Builder().addressId(999997L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999996)).build();
        address = new Address.Builder().addressId(999996L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        dbLog = new DBLog.Builder().timestamp(currentTimeStamp).application(Utility.getApplication()).machineName(Utility.getHostName()).userId(String.valueOf(999995)).build();
        address = new Address.Builder().addressId(999995L).houseNo("plot no 4").street("dwaraka nagar cology").villageTownCity("kistareddypet").landmark("patancheru").district("sangareddy").state("telangana").pincode(502139L).status("I").dbLog(dbLog).build();
        Assert.assertEquals(1, addressRepository.saveAddress(address));
        logger.info(getLoggerInfo() + "createDataForAddress(): data" + addressRepository.findAllAddress());
        logger.info(getLoggerInfo() + "### ended createDataForAddress() ###");
    }

    private static void cleanupDataForAddress() {
        logger.info(getLoggerInfo() + "### started cleanupDataForAddress() ###");
        logger.info(getLoggerInfo() + "cleanupDataForAddress(): data before cleanup: " + addressRepository.findAllAddress());
        addressRepository.cleanUp();
        logger.info(getLoggerInfo() + "cleanupDataForAddress(): data after cleanup: " + addressRepository.findAllAddress());
        logger.info(getLoggerInfo() + "### ended cleanupDataForAddress() ###");

    }

    private static String getLoggerInfo() {
        return Utility.getCurrrentTimeStamp() + ": " + AddressEndToEndTest.class.getName() + ": ";
    }
}

