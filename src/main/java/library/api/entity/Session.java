package library.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idCard;
    private Long idBook;
    private Date date_borrowed;
    private Date expiration_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "idCard",updatable = false,insertable = false)
    private CardLibrary card;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBook",insertable = false,updatable = false)
    private Book book;

    public void set(Session session){
        if(session.getDate_borrowed()!=null){
            setDate_borrowed(session.getDate_borrowed());
        }
        if(session.getExpiration_date()!=null){
            setExpiration_date(session.getExpiration_date());
        }
        if (session.getStatus() != null){
            setStatus(session.getStatus());
        }

    }

    public Session(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long numbercard) {
        this.idCard = numbercard;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long booknumber) {
        this.idBook = booknumber;
    }

    public CardLibrary getCard() {
        return card;
    }

    public void setCard(CardLibrary card) {
        this.card = card;
    }

    @JsonBackReference
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate_borrowed() {
        return date_borrowed;
    }

    public void setDate_borrowed(Date date_borrowed) {
        this.date_borrowed = date_borrowed;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
