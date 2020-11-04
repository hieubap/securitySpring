package library.entity;

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
    private Long numbercard;
    private Long booknumber;
    @ManyToOne
    @JoinColumn(name = "numbercard",updatable = false,insertable = false)
    private CardLibrary card;
/*
      ??????????????
      JSON parse error: Cannot construct instance of `library.entity.Book` (although at least one Creator exists):
      no int/Int-argument constructor/factory method to deserialize from Number value (5)
      thiếu set và get cho booknumber hoặc nhầm  book với booknumber


*/
    @OneToOne(orphanRemoval = false)
    /*
    nếu không insert và update = false thì sẽ bị thay đổi dữ liệu khi chỉnh sửa tại session
     */
    @JoinColumn(name = "booknumber",insertable = false,updatable = false)
    private Book book;

    private Date date_borrowed;
    private Date expiration_date;
    private String status;

    public Session(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(Long numbercard) {
        this.numbercard = numbercard;
    }

    public Long getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(Long booknumber) {
        this.booknumber = booknumber;
    }

    @JsonBackReference
    public CardLibrary getCard() {
        return card;
    }

    public void setCard(CardLibrary card) {
        this.card = card;
    }

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
