package library.security.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static library.security.enums.PermissionEnum.*;

public enum RoleEnum {
    USER(Sets.newHashSet(USER_READ,USER_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE,ADMIN_ROLE));

    public final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }
}
