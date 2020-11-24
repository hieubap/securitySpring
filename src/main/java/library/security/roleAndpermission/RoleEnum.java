package library.security.roleAndpermission;

import com.google.common.collect.Sets;

import java.util.Set;

import static library.security.roleAndpermission.PermissionEnum.ADMIN_READ;
import static library.security.roleAndpermission.PermissionEnum.STUDENT_READ;

public enum RoleEnum {
    STUDENT(Sets.newHashSet(STUDENT_READ)),
    ADMIN(Sets.newHashSet(ADMIN_READ));

    public final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
