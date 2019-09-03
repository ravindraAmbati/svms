package svms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;

public class Utility {
    private static String applicationName = "svms";
    private static String version = "1.0v";

    public static String getCurrrentTimeStamp() {
        return new Timestamp(new Date().getTime()).toString();
    }

    public static String getApplication() {
        return applicationName + "-" + version;
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "UnknownHostException Occured";
        }
    }

    public static String getUserId(int id) {
        return String.valueOf(id);
    }

    public static String getTimeStamp(int year, int month, int date) {
        return new Timestamp(new Date(year, month, date).getTime()).toString();
    }
}
