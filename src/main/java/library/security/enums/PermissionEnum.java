package library.security.enums;

public enum PermissionEnum {
    USER_READ("USER_READ"),
    USER_WRITE("USER_WRITE"),
    ADMIN_ROLE("admin");

    public final String permission;

    PermissionEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
