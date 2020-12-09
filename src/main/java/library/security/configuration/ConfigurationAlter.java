package library.security.configuration;

import library.security.filter.JwtTokenVerifierFilter;
import library.security.filter.JwtUsernamePasswordAuthenticationFilter;
import library.security.userdetail.UserDetailServiceAlter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;

import static library.security.enums.PermissionEnum.USER_READ;
import static library.security.enums.RoleEnum.ADMIN;
import static library.security.enums.RoleEnum.USER;

@Configuration
@EnableWebSecurity
public class ConfigurationAlter extends WebSecurityConfigurerAdapter {
    private UserDetailServiceAlter userService;
    private SecretKey secretKey;
    private JwtPropertiesConfiguration jwtConfigProperties;

    @Autowired
    public ConfigurationAlter(UserDetailServiceAlter userService, JwtPropertiesConfiguration jwtConfigProperties, SecretKey secretKey){
        this.userService = userService;
        this.jwtConfigProperties = jwtConfigProperties;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("authentication");
        auth.userDetailsService(userService);
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setUserDetailsService(userService);
//        dao.setPasswordEncoder(passwordEncoder());
//        System.out.println("line 35");
//        auth.authenticationProvider(dao);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("authority");

        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .addFilterAt(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfigProperties,secretKey),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfigProperties,secretKey), JwtUsernamePasswordAuthenticationFilter.class);

        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()

                .antMatchers("/student/**")
                .hasAnyRole(USER.name())
                .antMatchers("/books/**")
                .hasAnyAuthority(USER_READ.getPermission())
                .antMatchers("/card/**").hasAnyRole(ADMIN.name())
                .antMatchers("/headbook/**").hasAuthority(USER_READ.getPermission())

                .and()
                .rememberMe()
                .rememberMeCookieName("remember-me")
                .rememberMeParameter("remember")
                .key("remember")
                .alwaysRemember(true)

                .and()
                .logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID","remember-me");

        http
                .authorizeRequests()
                .antMatchers("/manager/**").permitAll()
                .and().httpBasic();
    }

//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new UsernameNotFoundException();
//    }

//    @Bean
//    public AuthenticationFilter authenticationFilter() throws Exception{
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
//        authenticationFilter.setUsernameParameter("username");
//        authenticationFilter.setPasswordParameter("password");
//        authenticationFilter.setAuthenticationManager(authenticationManager());
//        authenticationFilter.setFilterProcessesUrl("/login");
//        authenticationFilter.setAuthenticationSuccessHandler(successHandler());
//        return authenticationFilter;
//    }
}
