//package library.security.service;
//
//
//import library.security.model.Authority;
//import library.security.model.User;
//import library.security.repository.RoleRepository;
//import library.security.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserServiceInterface {
//	private library.security.repository.UserRepository userRepository;
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
//		super();
//		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
//	}
//
//	@Override
//	public User save(User user1) {
////		Role r;
////		if(roleRepository.findByName("USER"))
//		User user = new User(user1.getUsername(),
//				passwordEncoder.encode(user1.getPassword()), roleRepository.findByName("USER").getId());
//
//		return userRepository.save(user);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userRepository.findByUsername(username);
//
//		if(user == null) {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		System.out.println("name = " + username);
//		System.out.println("pass = " + user.getPassword());
//		System.out.println("role = " + user.getRole().getName());
//
//		return new UserDetail(username,user.getPassword(), Arrays.stream(user.getUsername().split(","))
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList()));
//	}
//
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}
//
//}
