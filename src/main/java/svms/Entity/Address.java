package svms.Entity;

public class Address {

    private Long addressId;
    private String houseNo;
    private String street;
    private String landmark;
    private String villageTownCity;
    private String district;
    private String state;
    private Long pincode;
    private String status;
    private DBLog dBLog;

    private Address(Builder builder){
        this.addressId = builder.addressId;
        this.houseNo = builder.houseNo;
        this.street = builder.street;
        this.landmark = builder.landmark;
        this.villageTownCity = builder.villageTownCity;
        this.district = builder.district;
        this.state = builder.state;
        this.pincode = builder.pincode;
        this.status = builder.status;
        this.dBLog = builder.dBLog;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getStreet() {
        return street;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getVillageTownCity() {
        return villageTownCity;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public Long getPincode() {
        return pincode;
    }

    public String getStatus() {
        return status;
    }

    public DBLog getDBLog() {
        return dBLog;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", houseNo='" + houseNo + '\'' +
                ", street='" + street + '\'' +
                ", landmark='" + landmark + '\'' +
                ", villageTownCity='" + villageTownCity + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", status='" + status + '\'' +
                ", dBLog=" + dBLog +
                '}';
    }

    public static class Builder {
        private Long addressId;
        private String houseNo;
        private String street;
        private String landmark;
        private String villageTownCity;
        private String district;
        private String state;
        private Long pincode;
        private String status;
        private DBLog dBLog;

        public Builder addressId(Long addressId){
            this.addressId = addressId;
            return this;
        }

        public Builder houseNo(String houseNo){
            this.houseNo = houseNo;
            return this;
        }

        public Builder street(String street){
            this.street = street;
            return this;
        }

        public Builder landmark(String landmark){
            this.landmark = landmark;
            return this;
        }

        public Builder villageTownCity(String villageTownCity){
            this.villageTownCity = villageTownCity;
            return this;
        }

        public Builder district(String district){
            this.district = district;
            return this;
        }

        public Builder state(String state){
            this.state = state;
            return this;
        }

        public Builder pincode(Long pincode){
            this.pincode = pincode;
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

        public Address build(){
            return new Address(this);
        }
    }

}
