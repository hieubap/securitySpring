package library.security.roleAndpermission;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static library.security.roleAndpermission.PermissionEnum.*;

public enum RoleEnum {
    STUDENT(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE)),
    MANAGER(Sets.newHashSet(MANAGER_READ,MANAGER_WRITE)),
    ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,MANAGER_WRITE,MANAGER_READ));

    public final Set<PermissionEnum> permissions;

    RoleEnum(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permission = getPermissions().stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permission;
    }
}
