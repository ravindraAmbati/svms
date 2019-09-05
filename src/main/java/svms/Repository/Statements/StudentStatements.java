package svms.Repository.Statements;

public class StudentStatements {
    public static final String FIND_ALL = "SELECT * FROM  student";
    public static final String FIND_BY_ID = "SELECT * FROM  student WHERE  studentid = :studentid";
    public static final String FIND_BY_STATUS = "SELECT * FROM  student WHERE status = :status";
    public static final String SAVE = "INSERT INTO  student VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM  student WHERE  studentid = ?";
    public static final String UPDATE_STATUS = "UPDATE  student SET status = ? WHERE  studentid = ?";
    public static final String UPDATE_FEE = "UPDATE  student SET fee = ? WHERE  studentid = ?";
    public static final String CLEAN_UP = "DELETE FROM  student";
}
