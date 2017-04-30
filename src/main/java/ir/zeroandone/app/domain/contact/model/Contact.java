package ir.zeroandone.app.domain.contact.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(columnDefinition = "nvarchar(50)")
    private String app;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    @Column(columnDefinition = "nvarchar(50)")
    private String email;

    @Column(columnDefinition = "nvarchar(50)")
    private String cellPhone;

    @NotNull
    @Column(columnDefinition = "nvarchar(50)")
    private String requestType;

    @NotNull
    @Column(columnDefinition = "nvarchar(250)")
    private String title;

    @NotNull
    @Column(columnDefinition = "nvarchar(1000)")
    private String message;

    public long getId() {
        return id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
