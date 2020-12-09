package library.security.userdetail;

import library.security.model.Authority;
import library.security.model.User;
import library.security.repository.RoleRepository;
import library.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceAlter implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailServiceAlter(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User save(User user1) {
        User user = new User(user1.getUsername(),
                passwordEncoder.encode(user1.getPassword()), roleRepository.findByName("USER").getId());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            System.out.println("null user");
            throw new UsernameNotFoundException("username not found exception in service");
        }


        UserDetails userDetails = new UserDetailAlter(user);
        user.getRole().getAuthorities().add(new Authority("ROLE_"+user.getRole().getName()));
        System.out.println("\nname = " + username);
        System.out.println("pass = " + user.getPassword());
        System.out.println("role = " + user.getRole().getName() + "\nauthority:");
        for(Authority auth : user.getRole().getAuthorities()) {
            System.out.println("    "+auth.getAuthority());
        }
        return userDetails;
    }

}
