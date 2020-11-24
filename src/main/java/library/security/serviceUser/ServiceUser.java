package library.security.serviceUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceUser implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    public ServiceUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserDetails> list = Arrays.asList(
                User.builder()
                        .username("student")
                        .password(passwordEncoder.encode("1"))
                        .roles("STUDENT")
                        .build(),
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("2"))
                        .roles("ADMIN")
                        .build()
        );

        return list.stream()
                .filter(userDetails -> s.equals(userDetails.getUsername())).findFirst().orElseThrow();
    }


}
