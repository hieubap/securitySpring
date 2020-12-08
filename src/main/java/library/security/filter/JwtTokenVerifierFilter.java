package library.security.filter;

import library.security.configuration.JwtPropertiesConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JwtTokenVerifierFilter extends OncePerRequestFilter{
    private final JwtPropertiesConfiguration jwtConfigProperties;
    private final SecretKey secretKey;

    public JwtTokenVerifierFilter(JwtPropertiesConfiguration jwtConfigProperties, SecretKey secretKey) {
        this.jwtConfigProperties = jwtConfigProperties;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader  = httpServletRequest.getHeader(jwtConfigProperties.getAuthorizationHeader());

//        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfigProperties.getTokenPrefix())){
//            filterChain.doFilter(httpServletRequest,httpServletResponse);
//            return;
//        }
//
//        String token = authorizationHeader.replace(jwtConfigProperties.getTokenPrefix(), "");
//
//        try{
//
//            Jws<Claims> claimsJws = Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJws(token);
//
//            Claims body = claimsJws.getBody();
//            String username = body.getSubject();
//
//            var authority = (List<Map<String,String>>) body.get("authorities");
//
//            Set<SimpleGrantedAuthority> grantetAuth = authority.stream()
//                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
//                    .collect(Collectors.toSet());
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    username,
//                    null,
//                    grantetAuth
//            );
//
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }catch (JwtException e){
//            throw new IllegalStateException(String.format("token %s cannot truest",token));
//        }

        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "hieu";
            }
        };
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
