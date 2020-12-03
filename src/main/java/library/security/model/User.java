package library.security.model;

import javax.persistence.*;

@Entity
@Table(name =  "user")
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private Long role_id;
	
	@ManyToOne//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "users_roles",
//			joinColumns = @JoinColumn(
//		            name = "user_id", referencedColumnName = "id"),
//			inverseJoinColumns = @JoinColumn(
//				            name = "role_id", referencedColumnName = "id"))
	@JoinColumn(name = "role_id",updatable = false,insertable = false)
	private Role role;

	public User() {
		role = new Role("ROLE_USER");
	}

	public User(String username, String password, Long roles) {
		this.username = username;
		this.password = password;
		this.role_id = roles;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
