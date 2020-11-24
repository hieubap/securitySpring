package library.security.roleAndpermission;

public enum PermissionEnum {
    STUDENT_READ("student::read"),
    STUDENT_WRITE("student::write"),
    ADMIN_READ("admin");

    public final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
