package library.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    private Long mssv;
    private String name;
    private String institute;
    private String cource;

    /*
    Cannot add or update a child row: a foreign key constraint fails (`mydatabase`.`card`, CONSTRAINT
     `card_student_id_fk` FOREIGN KEY (`mssv`) REFERENCES `student` (`id`))

     vì khóa ngoại card_student_id_fk từ card trỏ vào nó nên gây ra lỗi
     đáng nhẽ phải là khóa ngoại từ student trỏ tới card
     */
    @OneToOne(mappedBy = "student_info")
    private CardLibrary cardLibrary;

    public Student(){}

    public Long getMssv() {
        return mssv;
    }

    public void setMssv(Long mssv) {
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
}
