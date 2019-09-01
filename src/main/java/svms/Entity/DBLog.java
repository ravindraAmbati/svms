package svms.Entity;

public class DBLog {
    private String timestamp;
    private String application;
    private String machineName;
    private String userId;
    private DBLog(Builder builder){
        this.timestamp = builder.timestamp;
        this.application = builder.application;
        this.machineName = builder.machineName;
        this.userId = builder.userId;
    }

    public DBLog() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getApplication() {
        return application;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DBLog{" +
                "timestamp='" + timestamp + '\'' +
                ", application='" + application + '\'' +
                ", machineName='" + machineName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static class Builder{
        private String timestamp;
        private String application;
        private String machineName;
        private String userId;

        public Builder timestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public Builder application(String application){
            this.application = application;
            return  this;
        }

        public Builder machineName(String machineName){
            this.machineName = machineName;
            return  this;
        }

        public  Builder userId(String userId){
            this.userId = userId;
            return this;
        }

        public DBLog build(){
            return new DBLog(this);
        }
    }
}
