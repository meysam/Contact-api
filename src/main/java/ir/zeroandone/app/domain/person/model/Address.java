package ir.zeroandone.app.domain.person.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    @Column(columnDefinition = "nvarchar(50)")
    private String country;

    @Column(columnDefinition = "nvarchar(50)")
    private String ostan;

    @Column(columnDefinition = "nvarchar(50)")
    private String shahrestan;

    @Column(columnDefinition = "nvarchar(50)")
    private String bakhsh;

    @Column(columnDefinition = "nvarchar(50)")
    private String dehestan;

    @Column(columnDefinition = "nvarchar(50)")
    private String dehyari;

    @Column(columnDefinition = "nvarchar(50)")
    private String type;

    @Column(columnDefinition = "nvarchar(50)")
    private String areaCode;

    @NotNull
    @Column(columnDefinition = "nvarchar(300)")
    private String localAddress;

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getShahrestan() {
        return shahrestan;
    }

    public void setShahrestan(String shahrestan) {
        this.shahrestan = shahrestan;
    }

    public String getBakhsh() {
        return bakhsh;
    }

    public void setBakhsh(String bakhsh) {
        this.bakhsh = bakhsh;
    }

    public String getDehestan() {
        return dehestan;
    }

    public void setDehestan(String dehestan) {
        this.dehestan = dehestan;
    }

    public String getDehyari() {
        return dehyari;
    }

    public void setDehyari(String dehyari) {
        this.dehyari = dehyari;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}
