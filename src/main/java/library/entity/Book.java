package library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String status;
    private Long bookshelveid;
    private Long roomnumber;

    /*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    có thể khai báo ntn để có thể sửa lại trường id_borrow chứa khóa ngoại
    => không cần truyền hẳn Session nên không sợ mất mát dữ liệu từ
    bảng borrowbook


     */

    /*
    cascade = All thì khi xóa session thì book cũng bị xóa theo
    orphanRemoval cho phép xóa dữ liệu ngay cả khi có khóa ngoại từ entity cha chiếu tới nó

     */
    @OneToOne(mappedBy = "book",orphanRemoval = true)
//    @JoinColumn(name = "sessionid",updatable = false,insertable = false)
    private Session borrowBook;

    public Book(){}


    public Long getBookshelveid() {
        return bookshelveid;
    }

    public void setBookshelveid(Long bookshelveid) {
        this.bookshelveid = bookshelveid;
    }

    public Long getRoomnumbber() {
        return roomnumber;
    }

    public void setRoomnumbber(Long roomnumbber) {
        this.roomnumber = roomnumbber;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
