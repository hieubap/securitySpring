package library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

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

    /*
    ?????????????????  Bug  ?????????????????
    Cannot delete or update a parent row: a foreign key constraint fails ......
    khi thay đổi entity sẽ làm ảnh hưởng tới thông tin trường từ entity khác
    có khóa ngoại tới entity này
    =>
    + xóa các forgein key không cần thiết
    + để cascade ở all hoặc remove (!!!!!!!!!!!!!!!!!!)

     */
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn( name = "id_student")
    /*
    toàn bộ tương tác với student ở đây sẽ thay đổi thông tin trong bảng student
    + nếu tạo một student mới ở đây thì trong bảng student sẽ tạo ra một student mới.
    + còn nếu cập nhật student tại đây thì các trường trong student cũng thay đổi theo

    *** trùng id thì cập nhật còn khác thì tạo mới

    !!!!!!!!!!!  lưu ý
    nếu lúc truyền json chỉ truyền duy nhất giá trị id thì toàn bộ các
    thông tin khác trong student sẽ null hết
    !!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    private Student studentid;
    public Book(){}

    /*
    ????????????   Bug  ?????????????????????????
    StackOverflowError
    => @JsonBackReference để tránh lỗi khi truy vấn dữ liệu
     */
    @JsonBackReference
    /*
    dùng chữ cái đầu in thường không sẽ bị null khi request từ json (chưa chắc)
     */
    public Student getStudents() {
        return studentid;
    }

    /*
    ????????????   Bug  ?????????????????????????
    + not-null property references a null or transient value : library.entity.Book.studentid
    $$$$$$$$$$$ Solve $$$$$$$$$$$$$$
    vì trường studentid ta set cho nó not null nên mới có lỗi trên
    + nếu không nhất thiết khác null thì bỏ nullable = false chỗ @JoinColumn của entity này
    + do truyền vào "studentid" hoặc "students" : 4 => truyền trong {}  (!!!!!!!!!!)
    + lúc truyền phải đúng tên trường: nếu entity có trường Student tên students thì tên trường trong
    json cũng phải là students và ngược lại với studentid cx vậy
    đương nhiên getter và setter cx phải để đúng tên
    /////

    khi đặt tên setter và getter cho studentid thì phải đặt tên đúng với thuộc tính.
    mặc định hệ thống đặt cho setStudents thì ta đổi thành đúng tên thuộc tính
    là setStudentid

    vì Student ta đặt tên là studentid chứ không phải students.
    Nếu không có thể xảy ra lỗi null value ...
     */
    public void setStudent(Student students) {
        System.out.println("id student = " + students.getId());
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

    @NotNull
    public void setStatus(String status) {
        System.out.println("status = " + status);
        if(status == null) return;
        this.status = status;
    }
}
