package svms.Repository.Statements;

public class AddressStatements {

    public static final String FIND_ALL = "SELECT * FROM address";
    public static final String FIND_BY_ID = "SELECT * FROM address WHERE addressid = :addressid";
    public static final String FIND_BY_STATUS = "SELECT * FROM address WHERE status = :status";
    public static final String SAVE = "INSERT INTO address VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String DELETE = "DELETE FROM address WHERE addressid = ?";
    public static final String UPDATE_STATUS = "UPDATE address SET status = ? WHERE addressid = ?";
    public static final String CLEAN_UP = "DELETE FROM address";

}
