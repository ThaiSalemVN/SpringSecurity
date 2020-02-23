package thai.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import thai.security.dto.UserDTO;
import thai.security.entity.UserEntity;
import thai.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiểm tra xem user có tồn tại trong database không?
		UserEntity userEntity = userRepository.findById(username).orElse(null);
		if (userEntity==null) {
			   throw new UsernameNotFoundException(username);
        }
        return new UserDTO(userEntity);
	}
	

}
