package library.security.serviceUser;

import library.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	public User save(User user);
}