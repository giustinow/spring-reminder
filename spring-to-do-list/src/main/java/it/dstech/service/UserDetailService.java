package it.dstech.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dstech.model.Ruolo;
import it.dstech.model.User;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
	public UserDetails loadUserByUsername(String email)  {
		 User user = userService.findUserByEmail(email);
			 List<GrantedAuthority> authorities = getUserAuthority(user.getRuolo());
			 return buildUserForAuthentication(user, authorities);
	}


   private List<GrantedAuthority> getUserAuthority(Set<Ruolo> userRoles) {
       Set<GrantedAuthority> roles = new HashSet<>();
       for (Ruolo role : userRoles) {
           roles.add(new SimpleGrantedAuthority(role.getRuolo()));
       }
       return new ArrayList<>(roles);
   }

   private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
               user.getActive(), true, true, true, authorities);
   }

}
