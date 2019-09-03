package svms.Entity;

public class Employee {

    private Long employeeId;
    private Long personId;
    private Long classId;
    private String role;
    private Long salary;
    private String status;
    private DBLog dBlog;

    private Employee(Builder builder){
        this.employeeId = builder.employeeId;
        this.personId = builder.personId;
        this.classId = builder.classId;
        this.role = builder.role;
        this.salary = builder.salary;
        this.status = builder.status;
        this.dBlog = builder.dBlog;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getPersonId() {
        return personId;
    }

    public Long getClassId() {
        return classId;
    }

    public String getRole() {
        return role;
    }

    public Long getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    public DBLog getDBLog() {
        return dBlog;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", personId=" + personId +
                ", classId=" + classId +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", dBlog=" + dBlog +
                '}';
    }

    public static class Builder{
        private Long employeeId;
        private Long personId;
        private Long classId;
        private String role;
        private Long salary;
        private String status;
        private DBLog dBlog;

        public Builder employeeId(Long employeeId){
            this.employeeId = employeeId;
            return this;
        }

        public Builder personId(Long personId){
            this.personId = personId;
            return this;
        }

        public Builder classId(Long classId){
            this.classId = classId;
            return this;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }

        public Builder salary(Long salary){
            this.salary = salary;
            return this;
        }

        public Builder status(String status){
            this.status = status;
            return this;
        }

        public Builder dBLog(DBLog dBlog){
            this.dBlog = dBlog;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
