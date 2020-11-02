package library.entity;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;

@Entity
@Table(name = "borrowbook")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Student id_student;

    @ManyToOne
    private Book id_book;

    private Date expirydate;
    private String status;

    public BorrowBook(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
