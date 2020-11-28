package library.security.roleAndpermission;

public enum PermissionEnum {
    STUDENT_READ("student::read"),
    STUDENT_WRITE("student::write"),
    MANAGER_READ("manager::read"),
    MANAGER_WRITE("manager::write"),
    ADMIN_ROLE("admin");

    public final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
