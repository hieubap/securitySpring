//package library.security.serviceUser;
//
//import library.api.exceptionhandle.exception.ExistException;
//import library.security.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ServiceUser implements UserDetailsService {
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    private UserRepository userRepository;
//
//    public ServiceUser(PasswordEncoder passwordEncoder,UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
////        List<UserDetails> list = Arrays.asList(
////                User.builder()
////                        .username("student")
////                        .password(passwordEncoder.encode("1"))
//////                        .roles("STUDENT")
////                        .authorities(STUDENT.getGrantedAuthorities())
////                        .build(),
////                User.builder()
////                        .username("manager")
////                        .password(passwordEncoder.encode("3"))
//////                        .roles("MANAGER")
////                        .authorities(MANAGER.getGrantedAuthorities())
////                        .build(),
////                User.builder()
////                        .username("admin")
////                        .password(passwordEncoder.encode("2"))
//////                        .roles("ADMIN")
////                        .authorities(ADMIN.getGrantedAuthorities())
////                        .build()
////        );
////
////        return list.stream()
////                .filter(userDetails -> s.equals(userDetails.getUsername())).findFirst().orElseThrow();
//    }
//
//    public void save(User user){
//        if (userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword())){
//            throw new ExistException();
//        };
//        userRepository.save(user);
//    }
//
//
//
//
//}
