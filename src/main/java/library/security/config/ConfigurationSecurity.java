package library.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static library.security.roleAndpermission.RoleEnum.ADMIN;
import static library.security.roleAndpermission.RoleEnum.STUDENT;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/**").hasRole(STUDENT.name())
                .antMatchers("/card/**").hasRole(ADMIN.name())
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
