//package library.security.filter;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class BasicAuthenticationFilterAlter extends BasicAuthenticationFilter {
//    private AuthenticationManager authenticationManager;
//    public BasicAuthenticationFilterAlter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    public BasicAuthenticationFilterAlter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        boolean debug = this.logger.isDebugEnabled();
//
//        try {
//            UsernamePasswordAuthenticationToken authRequest = this.authenticationConverter.convert(request);
//            if (authRequest == null) {
//                chain.doFilter(request, response);
//                return;
//            }
//
//            String username = authRequest.getName();
//            if (debug) {
//                this.logger.debug("Basic Authentication Authorization header found for user '" + username + "'");
//            }
//
//            if (this.authenticationIsRequired(username)) {
//                Authentication authResult = this.authenticationManager.authenticate(authRequest);
//                if (debug) {
//                    this.logger.debug("Authentication success: " + authResult);
//                }
//
//                SecurityContextHolder.getContext().setAuthentication(authResult);
//                this.rememberMeServices.loginSuccess(request, response, authResult);
//                this.onSuccessfulAuthentication(request, response, authResult);
//            }
//        } catch (AuthenticationException var8) {
//            SecurityContextHolder.clearContext();
//            if (debug) {
//                this.logger.debug("Authentication request for failed!", var8);
//            }
//
//            this.rememberMeServices.loginFail(request, response);
//            this.onUnsuccessfulAuthentication(request, response, var8);
//            if (this.ignoreFailure) {
//                chain.doFilter(request, response);
//            } else {
//                this.authenticationEntryPoint.commence(request, response, var8);
//            }
//
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//}
