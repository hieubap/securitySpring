package library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "card")
public class CardLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numbercard;
    private String mssv;
    private Timestamp expiration_date;
    private String status;
    private Long studentId;

    @OneToOne(cascade = CascadeType.ALL) // DDL error
    @JoinColumn(name = "studentId",insertable = false,updatable = false)
    private Student student_info;

    /*
    java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row:
    a foreign key constraint fails (`mydatabase`.`session`, CONSTRAINT `FKguwoeh5u8ubma06drp6vtonf7`
    FOREIGN KEY (`numbercard`) REFERENCES `card` (`numbercard`))

    ?????????????????????? Bug  ??????????//
    Referencing column 'mssv' and referenced column 'mssv'
    in foreign key constraint 'FKrwvc3laoe7gubto2j9lm8w6n1' are incompatible.
    => lỗi không tương thích dữ liệu: varchar và bigint
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<Session> list_sessions;



    public CardLibrary() {}

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public Long getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(Long numbercard) {
        this.numbercard = numbercard;
    }

    @JsonBackReference
    public Student getStudent_info() {
        return student_info;
    }

    public void setStudent_info(Student studentid) {
        this.student_info = studentid;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }

    @JsonBackReference
    public List<Session> getList_sessions() {
        return list_sessions;
    }

    public void setList_sessions(List<Session> listsesions) {
        this.list_sessions = listsesions;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
