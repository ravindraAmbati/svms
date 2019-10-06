package svms.Entity;

public class Class {
    private Long classId;
    private String name;
    private int noOfBoys;
    private int noOfGirls;
    private Long employeeId;
    private String status;
    private DBLog dBLog;

    private Class(Builder builder){
        this.classId = builder.classId;
        this.name = builder.name;
        this.noOfBoys = builder.noOfBoys;
        this.noOfGirls = builder.noOfGirls;
        this.employeeId = builder.employeeId;
        this.status = builder.status;
        this.dBLog = builder.dBLog;
    }

    public Long getClassId() {
        return classId;
    }

    public String getName() {
        return name;
    }

    public int getNoOfBoys() {
        return noOfBoys;
    }

    public int getNoOfGirls() {
        return noOfGirls;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getStatus() {
        return status;
    }

    public DBLog getDBLog() {
        return dBLog;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", name='" + name + '\'' +
                ", noOfBoys=" + noOfBoys +
                ", noOfGirls=" + noOfGirls +
                ", employeeId=" + employeeId +
                ", status='" + status + '\'' +
                ", dBLog=" + dBLog +
                '}';
    }

    public static class Builder {
        private Long classId;
        private String name;
        private int noOfBoys;
        private int noOfGirls;
        private Long employeeId;
        private String status;
        private DBLog dBLog;

        public Builder classId(Long classId){
            this.classId = classId;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder noOfBoys(int noOfBoys){
            this.noOfBoys = noOfBoys;
            return this;
        }

        public Builder noOfGirls(int noOfGirls){
            this.noOfGirls = noOfGirls;
            return this;
        }

        public Builder employeeId(Long employeeId){
            this.employeeId = employeeId;
            return this;
        }

        public Builder status(String status){
            this.status = status;
            return this;
        }

        public Builder dBLog(DBLog dBLog){
            this.dBLog = dBLog;
            return this;
        }

        public Class build(){
            return new Class(this);
        }
    }

}
