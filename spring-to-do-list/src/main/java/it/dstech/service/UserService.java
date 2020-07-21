package it.dstech.service;
import java.util.List;

import it.dstech.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);
	
	public User save(User user);
	
	public User getUser(Long id);
	
	public void delete(Long id);
	
	public List<User> listaStudenti();

}
