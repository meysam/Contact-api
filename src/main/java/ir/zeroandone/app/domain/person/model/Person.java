package ir.zeroandone.app.domain.person.model;

import ir.zeroandone.app.domain.person.validator.NationalId;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PERSON",uniqueConstraints ={@UniqueConstraint(columnNames = "nationalId")})
public class Person {

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = {CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "PersonAttachment", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private List<Attachment> attachments;

    @NotNull
    @NotEmpty
    @Column(columnDefinition = "nvarchar(50)")
    private String firstName;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String middleName;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String lastName;

    @NotNull
    private String englishFname;

    @NotNull
    private String englishLname;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String fatherName;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String gender;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String marital;

    @NationalId(message = "کد ملی صحیح وارد نمایید")
    @Size(min = 10, max = 10, message = "کد ملی باید ده رقم باشد")
    @NotNull
    @Column(columnDefinition = "nvarchar(10)")
    private String nationalId;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String birthCertificateId;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String placeOfBirth;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String issueDivision;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String birthDate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    @Column(columnDefinition = "nvarchar(12)")
    private String cellPhone;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String email;

    private String followingCode;

    @NotNull
    @Column(columnDefinition = "nvarchar(15)")
    private String accountNumber;

    @NotNull
    @Column(columnDefinition = "nvarchar(10)")
    private String phoneNumberPlan;

    @NotNull
    @Column(columnDefinition = "nvarchar(10)")
    private String phone;

    @NotNull
    @Column(columnDefinition = "nvarchar(11)")
    @Size(min = 10, max = 10, message = "کد پستی باید ده رقم باشد")
    private String postalCode;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String bankName;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String educationLevel;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String educationField;

    @NotNull
    @Column(columnDefinition = "nvarchar(30)")
    private String iban;

    @NotNull
    @Column(columnDefinition = "nvarchar(20)")
    private String cardNumber;





    private Date createdOn;
    private Date updatedOn;

    public long getId() {
        return id;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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

    public String getIssueDivision() {
        return issueDivision;
    }

    public void setIssueDivision(String issueDivision) {
        this.issueDivision = issueDivision;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public String getPhoneNumberPlan() {
        return phoneNumberPlan;
    }

    public void setPhoneNumberPlan(String phoneNumberPlan) {
        this.phoneNumberPlan = phoneNumberPlan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEducationField() {
        return educationField;
    }

    public void setEducationField(String educationField) {
        this.educationField = educationField;
    }
}
