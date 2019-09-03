package svms.Repository.Statements;

public class PersonStatements {
    public static final String FIND_ALL = "SELECT * FROM person";
    public static final String FIND_BY_ID = "SELECT * FROM person WHERE personid = :personid";
    public static final String FIND_BY_FIRSTNAME = "SELECT * FROM person WHERE first_name = :firstName";
    public static final String FIND_BY_STATUS = "SELECT * FROM person WHERE status = :status";
    public static final String FIND_BY_GENDER = "SELECT * FROM person WHERE gender = :gender";
    public static final String SAVE = "INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM person WHERE personid = ?";
    public static final String UPDATE_STATUS = "UPDATE person SET status = ? WHERE personid = ?";
    public static final String CLEAN_UP = "DELETE FROM person";
}
