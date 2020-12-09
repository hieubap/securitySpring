package library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

+ find theo id sách
+ xuất ra file excel(kiểm thư)
+ hiển thị api để tạp biểu đồ
+ ngày 1: bn ... => array
+ login: có quyền (sv,người quản lý)
+ mã số khác nhau. đầu sách
+ catecury..
+

role hay permission đều là string chỉ khác ở chỗ role nó gồm chuỗi các permission
nó không phân biệt được vì đó chỉ là chuỗi nên phải ghép role vào list permission
khi đưa ra màn hình
+ riêng role khi map luôn được ghép với ROLE_ đằng trc còn permission thì không -> nối tại entity
    thì mới xác thực được

+ entity authority extends GrantedAuthority khi lưu quyền vào database
    nối role vào collection permission để có mọi quyền

+ remember-me: sử dung cookie để tự động đăng nhập. mỗi khi đăng nhập đều có 1 jsessionid nhưng hạn
    của nó là cho tới khi ta thoát trình duyệt thì session hết hạn
    còn cookie thì hạn mặc định là 2 tuần
+ logout: xóa cookie đi

có 12 filter mặc định của hệ thống. không thể ném lỗi xác thực theo ý mình vì
các filter được chạy trước nên phải ném tại filter.
+ thêm filter vào filterchainproxy

+jwt: tạo được jwt và xác thực bằng jwt.

 */
@SpringBootApplication
public class SpringJPALibraryManager {
    public static void main(String[] args) {
        SpringApplication.run(SpringJPALibraryManager.class,args);
    }
}
