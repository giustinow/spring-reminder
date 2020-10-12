package it.dstech.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.dstech.model.Ruolo;
import it.dstech.model.User;
import it.dstech.repository.RuoloRepository;
import it.dstech.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		User findUser = userRepository.findByEmail(user.getEmail());
		
		if(findUser == null) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(true);
	        Ruolo userRole = ruoloRepository.findByRuolo("UTENTE");
	        user.setRuolo(new HashSet<Ruolo>(Arrays.asList(userRole)));
		}
	        return userRepository.save(user);
	}
	
	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> listaStudenti() {
		return userRepository.findAll();
	}
}
