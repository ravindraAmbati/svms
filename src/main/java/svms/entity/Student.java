package svms.entity;

public class Student {
    private Long studentId;
    private Long personId;
    private Long classId;
    private Long fee;
    private String status;
    private DBLog dBLog;

    private Student(Builder builder) {
        this.studentId = builder.studentId;
        this.personId = builder.personId;
        this.classId = builder.classId;
        this.fee = builder.fee;
        this.status = builder.status;
        this.dBLog = builder.dBLog;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getPersonId() {
        return personId;
    }

    public Long getClassId() {
        return classId;
    }

    public Long getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public DBLog getDBLog() {
        return dBLog;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", personId=" + personId +
                ", classId=" + classId +
                ", fee=" + fee +
                ", status='" + status + '\'' +
                ", dBLog=" + dBLog +
                '}';
    }

    public static class Builder {
        private Long studentId;
        private Long personId;
        private Long classId;
        private Long fee;
        private String status;
        private DBLog dBLog;

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Builder classId(Long classId) {
            this.classId = classId;
            return this;
        }

        public Builder fee(Long fee) {
            this.fee = fee;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder dBLog(DBLog dBLog) {
            this.dBLog = dBLog;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
