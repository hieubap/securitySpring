package library.security.userdetail;

import library.exceptionhandle.exception.UsernameNotFoundException;
import library.security.model.User;
import library.security.repository.RoleRepository;
import library.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServices implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailServices(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User save(User user1) {
        User user = new User(user1.getUsername(),
                passwordEncoder.encode(user1.getPassword()), roleRepository.findByName("USER").getId());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        throw new UsernameNotFoundException(username);
        User user = userRepository.findByUsername(username);

        if(user == null) {
            System.out.println("not find user");
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = new UserDetail(user);
        System.out.println("name = " + username);
        System.out.println("pass = " + user.getPassword());
        System.out.println("role = " + user.getRole().getName());

        return userDetails;
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
}
