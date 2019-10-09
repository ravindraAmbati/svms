package svms.entity;

public class Person {
    private Long personId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String dateOfJoin;
    private Long contact;
    private Long alternativeContact;
    private String status;
    private Long addressId;
    private DBLog dBLog;

    private Person(Builder builder) {
        this.personId = builder.personId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.dateOfBirth = builder.dateOfBirth;
        this.dateOfJoin = builder.dateOfJoin;
        this.contact = builder.contact;
        this.alternativeContact = builder.alternativeContact;
        this.status = builder.status;
        this.addressId = builder.addressId;
        this.dBLog = builder.dBLog;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfJoin() {
        return dateOfJoin;
    }

    public Long getContact() {
        return contact;
    }

    public Long getAlternativeContact() {
        return alternativeContact;
    }

    public String getStatus() {
        return status;
    }

    public Long getAddressId() {
        return addressId;
    }

    public DBLog getDBLog() {
        return dBLog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfJoin='" + dateOfJoin + '\'' +
                ", contact=" + contact +
                ", alternativeContact=" + alternativeContact +
                ", status='" + status + '\'' +
                ", addressId=" + addressId +
                ", dBLog=" + dBLog +
                '}';
    }

    public static class Builder {
        private Long personId;
        private String firstName;
        private String lastName;
        private String gender;
        private String dateOfBirth;
        private String dateOfJoin;
        private Long contact;
        private Long alternativeContact;
        private String status;
        private Long addressId;
        private DBLog dBLog;

        public Builder personId(Long personId) {
            this.personId = personId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder dateOfJoin(String dateOfJoin) {
            this.dateOfJoin = dateOfJoin;
            return this;
        }

        public Builder contact(Long contact) {
            this.contact = contact;
            return this;
        }

        public Builder alternativeContact(Long alternativeContact) {
            this.alternativeContact = alternativeContact;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder addressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder dBLog(DBLog dBLog) {
            this.dBLog = dBLog;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
