package svms.entity;

public class LoginUser {
    private Long id;
    private String username;
    private String password;
    private String status;
    private String loginTime;
    private String logoffTime;
    private String lastAttemptStatus;
    private DBLog dbLog;

    private LoginUser(Builder builder) {
        super();
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.status = builder.status;
        this.loginTime = builder.loginTime;
        this.logoffTime = builder.logoffTime;
        this.lastAttemptStatus = builder.lastAttemptStatus;
        this.dbLog = builder.dbLog;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getLogoffTime() {
        return logoffTime;
    }

    public String getLastAttemptStatus() {
        return lastAttemptStatus;
    }

    public DBLog getDBLog() {
        return dbLog;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoffTime='" + logoffTime + '\'' +
                ", lastAttemptStatus='" + lastAttemptStatus + '\'' +
                ", dbLog=" + dbLog +
                '}';
    }

    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private String status;
        private String loginTime;
        private String logoffTime;
        private String lastAttemptStatus;
        private DBLog dbLog;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder loginTime(String loginTime) {
            this.loginTime = loginTime;
            return this;
        }

        public Builder logoffTime(String logoffTime) {
            this.logoffTime = logoffTime;
            return this;
        }

        public Builder lastAttemptStatus(String lastAttemptStatus) {
            this.lastAttemptStatus = lastAttemptStatus;
            return this;
        }

        public Builder dbLog(DBLog dbLog) {
            this.dbLog = dbLog;
            return this;
        }

        public LoginUser build() {
            return new LoginUser(this);
        }
    }
}
