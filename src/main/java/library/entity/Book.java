package library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

    @ManyToOne( cascade = {CascadeType.REMOVE,CascadeType.ALL})
    @JoinColumn( name = "id_student", nullable = false)
    private Student studentid;

    public Book(){}

    /*
    để tránh lỗi stackoverflow khi lấy dữ liệu
     */
    @JsonBackReference
    /*
    dùng chữ cái đầu in thường không sẽ bị null khi request từ json
     */
    public Student getStudents() {
        return studentid;
    }

    public void setstudents(Student students) {
        System.out.println("null student");
        this.studentid = students;
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
