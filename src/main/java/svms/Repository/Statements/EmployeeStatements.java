package svms.Repository.Statements;

public class EmployeeStatements {
    public static final String FIND_ALL = "SELECT * FROM  employee";
    public static final String FIND_BY_ID = "SELECT * FROM  employee WHERE  employeeid = :employeeid";
    public static final String FIND_BY_STATUS = "SELECT * FROM  employee WHERE status = :status";
    public static final String FIND_BY_ROLE = "SELECT * FROM  employee WHERE role = :role";
    public static final String SAVE = "INSERT INTO  employee VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM  employee WHERE  employeeid = ?";
    public static final String UPDATE_STATUS = "UPDATE  employee SET status = ? WHERE  employeeid = ?";
    public static final String UPDATE_ROLE = "UPDATE  employee SET role = ? WHERE  employeeid = ?";
    public static final String UPDATE_SALARY = "UPDATE  employee SET salary = ? WHERE  employeeid = ?";
    public static final String CLEAN_UP = "DELETE FROM  employee";
}
