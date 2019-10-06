package svms.Repository.Statements;

public class ClassStatements {

    public static final String FIND_ALL = "SELECT * FROM class";
    public static final String FIND_BY_ID = "SELECT * FROM class WHERE classid = :classid";
    public static final String FIND_BY_EMPLOYEE_ID = "SELECT * FROM class WHERE employeeid = :employeeid";
    public static final String FIND_BY_STATUS = "SELECT * FROM class WHERE status = :status";
    public static final String SAVE = "INSERT INTO class VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM class WHERE classid = ?";
    public static final String UPDATE_STATUS = "UPDATE class SET status = ? WHERE classid = ?";
    public static final String UPDATE_EMPLOYEE_ID = "UPDATE class SET employeeid = ? WHERE classid = ?";
    public static final String CLEAN_UP = "DELETE FROM class";

}
