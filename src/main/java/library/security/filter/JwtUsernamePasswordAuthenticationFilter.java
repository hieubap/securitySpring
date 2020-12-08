package library.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import library.api.responceEntity.EntityResponse;
import library.security.DAO.UsernameAndPasswordDAO;
import library.security.configuration.JwtPropertiesConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtPropertiesConfiguration jwtConfigProperties;
    private final SecretKey secretKey;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtPropertiesConfiguration jwtConfigProperties, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfigProperties = jwtConfigProperties;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordDAO accountDAO = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordDAO.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    accountDAO.getUsername(),
                    accountDAO.getPassword()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);

            return authenticate;

        }catch (IOException e){
//            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfigProperties.getAuthorizationHeader(), jwtConfigProperties.getTokenPrefix() + token);
//        super.successfulAuthentication(request, response, chain, authResult);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("json");

        EntityResponse<Object> entityResponse = new EntityResponse(HttpStatus.NOT_FOUND,"dont success",null);
        String entityresponce = convertObjecttoString(entityResponse);

        PrintWriter printWriter = response.getWriter();
        printWriter.write(entityresponce);
        printWriter.flush();
    }
    public String convertObjecttoString(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }
}
