package library.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mssv;
    private String name;
    private String institute;
    private String cource;
    private String phone;
    private boolean gender;

    /*
    Cannot add or update a child row: a foreign key constraint fails (`mydatabase`.`card`, CONSTRAINT
     `card_student_id_fk` FOREIGN KEY (`mssv`) REFERENCES `student` (`id`))

     vì khóa ngoại card_student_id_fk từ card trỏ vào nó nên gây ra lỗi
     đáng nhẽ phải là khóa ngoại từ student trỏ tới card
     */
    @OneToOne(mappedBy = "student_info")
    private CardLibrary cardLibrary;

    public Student(){}

    public Student(Long id,String name,String mssv){
        this.id = id;
        this.name = name;
        this.mssv = mssv;
    }

    public void setAll(Student student){
        if (student.getName() != null)
            this.name = student.getName();
        if (student.getCource() != null)
            this.cource = student.getCource();
        if (student.getInstitute() != null)
            this.institute = student.getInstitute();
        if (student.getPhone() != null)
            this.phone = student.getPhone();

        this.gender = student.gender;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public CardLibrary getCardLibrary() {
        return cardLibrary;
    }

    public void setCardLibrary(CardLibrary cardLibrary) {
        this.cardLibrary = cardLibrary;
    }
}
