package svms.repository.statements;

public class LoginUserStatements {

    public static final String FIND_ALL = "SELECT * FROM loginuser";
    public static final String FIND_BY_ID = "SELECT * FROM loginuser WHERE id = :id";
    public static final String FIND_BY_USERNAME = "SELECT * FROM loginuser WHERE username = :username";
    public static final String FIND_BY_STATUS = "SELECT * FROM loginuser WHERE status = :status";
    public static final String FIND_BY_LAST_ATTEMPT_STATUS = "SELECT * FROM loginuser WHERE last_attempt_status = :lastattemptstatus";
    public static final String SAVE = "INSERT INTO loginuser VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM loginuser WHERE id = ?";
    public static final String UPDATE_PASSWORD = "UPDATE loginuser SET password = ? WHERE id = ?";
    public static final String UPDATE_STATUS = "UPDATE loginuser SET status = ? WHERE id = ?";
    public static final String CLEAN_UP = "DELETE FROM loginuser";
}
