package ir.zeroandone.app.domain.person.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment {

    public Attachment() {
    }

    public Attachment(long contentLength, String contentType, String fileName, String title, String createdOn, String updatedOn, byte[] content) {
        this.contentLength = contentLength;
        this.contentType = contentType;
        this.fileName = fileName;
        this.title = title;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ContentLength", nullable = false)
    private long contentLength;

    @Column(name = "ContentType", nullable = false)
    private String contentType;

    @Column(name = "FileName", nullable = false)
    private String fileName;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "CreatedOn", nullable = false)
    private String createdOn;

    @Column(name = "UpdatedOn", nullable = false)
    private String updatedOn;

    @Lob
    @Column(name = "Content", nullable = false, length=100000)
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
