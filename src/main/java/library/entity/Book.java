package library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long headBookId;
    private Timestamp addedDate;
    private String note;
    private String status;

    @ManyToOne
    @JoinColumn(name = "headBookId",insertable = false,updatable = false)
    private HeadBook headBook;

    @OneToOne(mappedBy = "book")
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public HeadBook getHeadBook() {
        return headBook;
    }

    public void set(Book book){
        if(book.getNote() != null){
            this.setNote(book.getNote());
        }
        if(book.getStatus() != null){
            this.setStatus(book.getStatus());
        }
        if(book.getAddedDate() != null){
            this.setAddedDate(book.getAddedDate());
        }
    }

    public void setHeadBook(HeadBook headBook) {
        this.headBook = headBook;
    }

    @OneToOne(mappedBy = "book",orphanRemoval = true)
    private Session borrowBook;

    public Book(){}

    @JsonBackReference
    public Session getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(Session borrowBook) {
        this.borrowBook = borrowBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String type) {
        this.note = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public Long getHeadBookId() {
        return headBookId;
    }

    public void setHeadBookId(Long headBookId) {
        this.headBookId = headBookId;
    }
}
