/*
entity:

    java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row:
    a foreign key constraint fails (`mydatabase`.`session`, CONSTRAINT `FKguwoeh5u8ubma06drp6vtonf7`
    FOREIGN KEY (`numbercard`) REFERENCES `card` (`numbercard`))

    ?????????????????????? Bug  ??????????//
    Referencing column 'mssv' and referenced column 'mssv'
    in foreign key constraint 'FKrwvc3laoe7gubto2j9lm8w6n1' are incompatible.
    => lỗi không tương thích dữ liệu: varchar và bigint
*/

/*
làm tắt: dùng objectmapper

    java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class
    library.entity.Student (java.util.LinkedHashMap is in module java.base of loader 'bootstrap';
    library.entity.Student is in unnamed module of loader 'app')


    UnrecognizedPropertyException: Unrecognized field "mssv" (class library.entity.Session), not marked as
    ignorable (8 known properties: "card", "booknumber", "date_borrowed", "numbercard", "id",
    "expiration_date", "status", "book"])

    quên break tại switch;
*/

/*
Book.java:

    !!!!!!!!!!!!!!!!!!!!!!!!!!!
    có thể khai báo ntn để có thể sửa lại trường id_borrow chứa khóa ngoại
    => không cần truyền hẳn Session nên không sợ mất mát dữ liệu từ
    bảng borrowbook


     */

    /*
    cascade = All thì khi xóa session thì book cũng bị xóa theo
    orphanRemoval cho phép xóa dữ liệu ngay cả khi có khóa ngoại từ entity cha chiếu tới nó

     */
/*  session: before getcard

dễ gây lỗi sau:
 Failed to evaluate Jackson deserialization for type [[simple type, class library.entity.Student]]:
 com.fasterxml.jackson.databind.JsonMappingException: Multiple back-reference properties with name
 'defaultReference'

      ??????????????
      JSON parse error: Cannot construct instance of `library.entity.Book` (although at least one Creator exists):
      no int/Int-argument constructor/factory method to deserialize from Number value (5)
      thiếu set và get cho booknumber hoặc nhầm  book với booknumber


*/

/*
EntiryReponse:

No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor

=>
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
private T data;
 */

