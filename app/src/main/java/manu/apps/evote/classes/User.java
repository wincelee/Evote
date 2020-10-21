package manu.apps.evote.classes;

public class User {

    private String email;
    private String idNumber;
    private String county;
    private String constituency;

    public User() {

    }

    public User(String email, String idNumber, String county, String constituency) {
        this.email = email;
        this.idNumber = idNumber;
        this.county = county;
        this.constituency = constituency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
