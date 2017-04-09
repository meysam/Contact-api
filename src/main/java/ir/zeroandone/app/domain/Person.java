package ir.zeroandone.app.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Person {

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Person(String firstName, String middleName, String lastName, String fatherName, String gender, String marital, String nationalId, String birthCertificateId, long birthDivisionId, String issueDivision, String birthDate, String address, Date createdOn, Date updatedOn) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.marital = marital;
        this.nationalId = nationalId;
        this.birthCertificateId = birthCertificateId;
        this.birthDivisionId = birthDivisionId;
        this.issueDivision = issueDivision;
        this.birthDate = birthDate;
        this.address = address;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String fatherName;

    @NotNull
    private String gender;

    @NotNull
    private String marital;

    @Size(min = 10,max = 10,message = "کد ملی باید ده رقم باشد")
    @NotNull
    private String nationalId;

    @NotNull
    private String birthCertificateId;

    @NotNull
    private long birthDivisionId;

    @NotNull
    private String issueDivision;

    @NotNull
    private String birthDate;

    @NotNull
    private String address;

    private Date createdOn;
    private Date updatedOn;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getBirthCertificateId() {
        return birthCertificateId;
    }

    public void setBirthCertificateId(String birthCertificateId) {
        this.birthCertificateId = birthCertificateId;
    }

    public long getBirthDivisionId() {
        return birthDivisionId;
    }

    public void setBirthDivisionId(long birthDivisionId) {
        this.birthDivisionId = birthDivisionId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getIssueDivision() {
        return issueDivision;
    }

    public void setIssueDivision(String issueDivision) {
        this.issueDivision = issueDivision;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
