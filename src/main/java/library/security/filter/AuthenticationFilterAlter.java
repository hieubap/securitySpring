//package library.security.filter;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//public class AuthenticationFilterAlter implements Filter {
//    private AuthenticationManager authenticationManager;
//
//    public AuthenticationFilterAlter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//        System.out.println("create AuthenticationFilter");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest http = (HttpServletRequest) servletRequest;
//        servletResponse.setContentType("json");
//        System.out.println(servletRequest);
//        System.out.println(http.getHeader("Body"));
//
//
//        String username = servletRequest.getParameter("username");
//        System.out.println("username = " + username);
//        String password = servletRequest.getParameter("password");
//        System.out.println("password = " + password);
//
//
////        BasicAuthenticationConverter converter = new BasicAuthenticationConverter();
//
////        UsernamePasswordAuthenticationToken token;
//
////        BasicAuthenticationFilter
////        PrintWriter printWriter =
////        servletResponse.getWriter();
////        printWriter.write(convertObjectToJson(new EntityResponse<Object>("username is not found")));
////        printWriter.flush();
//
//        filterChain.doFilter(servletRequest,servletResponse);
////        throw new UsernameNotFoundException("hehe okey");
//    }
//
//    public String convertObjectToJson(Object object) throws JsonProcessingException {
//        if (object == null) {
//            return null;
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
//}
