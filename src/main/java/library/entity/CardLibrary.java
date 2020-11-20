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
    private Long id;
    private String mssv;
    private Timestamp expiration_date;
    private String status;
    private Long studentId;

    @OneToOne(cascade = CascadeType.ALL) // DDL error
    @JoinColumn(name = "studentId",insertable = false,updatable = false)
    private Student student_info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<Session> list_sessions;

    public void set(CardLibrary cardLibrary){
        if(cardLibrary.getStatus() != null){
            this.setStatus(cardLibrary.getStatus());
        }
        if(cardLibrary.getExpiration_date() != null){
            this.setExpiration_date(cardLibrary.getExpiration_date());
        }
    }


    public CardLibrary() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @JsonBackReference
    public Student getStudent_info() {
        return student_info;
    }

    public void setStudent_info(Student student_info) {
        this.student_info = student_info;
    }

    @JsonBackReference
    public List<Session> getList_sessions() {
        return list_sessions;
    }

    public void setList_sessions(List<Session> list_sessions) {
        this.list_sessions = list_sessions;
    }
}
