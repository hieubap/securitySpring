package library.security.config;

import library.security.userdetail.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static library.security.enums.RoleEnum.ADMIN;
import static library.security.enums.RoleEnum.USER;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServices userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setUserDetailsService(userService);
//        dao.setPasswordEncoder(passwordEncoder());
//        System.out.println("line 35");
//        auth.authenticationProvider(dao);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("STRING=" + ADMIN.name());
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/student/**")
                .hasAnyRole(USER.name(), ADMIN.name())

                .antMatchers("/student/**", "/card/**").hasRole(USER.name())//hasRole(STUDENT.name())
                .antMatchers("/headbook/**").hasRole(USER.name())
                .and().httpBasic();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").hasRole(ADMIN.name())
                .and().httpBasic();


    }
}
