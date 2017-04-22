package ir.zeroandone.app.domain;

import ir.zeroandone.app.domain.validator.NationalId;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PersonMarketer")
public class Person {

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    @JoinTable(name="PersonAttachment",joinColumns = @JoinColumn( name="person_id"),
            inverseJoinColumns = @JoinColumn( name="attachment_id"))
            private Set<Attachment> attachments = new HashSet<Attachment>();

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String englishFname;

    @NotNull
    private String englishLname;

    @NotNull
    private String fatherName;

    @NotNull
    private String gender;

    @NotNull
    private String marital;

    @NationalId(message = "کد ملی صحیح وارد نمایید")
    @Size(min = 10, max = 10, message = "کد ملی باید ده رقم باشد")
    @NotNull
    private String nationalId;

    @NotNull
    private String birthCertificateId;

    @NotNull
    private String placeOfBirth;

    @NotNull
    private String issueDivision;

    @NotNull
    private String birthDate;

    @NotNull
    private String address;

    @NotNull
    private String cellPhone;

    @NotNull
    private String email;

    private String followingCode;

    @NotNull
    private String accountNumber;

    @NotNull
    private String phonNumberPlan;

    @NotNull
    private String phone;

    @NotNull
    private String postalCode;

    @NotNull
    private String bankName;


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

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowingCode() {
        return followingCode;
    }

    public void setFollowingCode(String followingCode) {
        this.followingCode = followingCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getEnglishFname() {
        return englishFname;
    }

    public void setEnglishFname(String englishFname) {
        this.englishFname = englishFname;
    }

    public String getEnglishLname() {
        return englishLname;
    }

    public void setEnglishLname(String englishLname) {
        this.englishLname = englishLname;
    }

    public String getPhonNumberPlan() {
        return phonNumberPlan;
    }

    public void setPhonNumberPlan(String phonNumberPlan) {
        this.phonNumberPlan = phonNumberPlan;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
