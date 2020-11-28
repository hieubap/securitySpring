package library.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static library.security.roleAndpermission.PermissionEnum.MANAGER_READ;
import static library.security.roleAndpermission.PermissionEnum.STUDENT_READ;
import static library.security.roleAndpermission.RoleEnum.MANAGER;
import static library.security.roleAndpermission.RoleEnum.STUDENT;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/student/**")
                .hasAnyAuthority(MANAGER_READ.getPermission(),STUDENT_READ.getPermission())

                .antMatchers("/student/**", "/card/**").hasRole(STUDENT.name())//hasRole(STUDENT.name())
                .antMatchers( "/headbook/**").hasRole(MANAGER.name())

                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().permitAll()
                .and().httpBasic();
    }
}
