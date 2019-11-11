package co.com.fhhf.deploymentfullapp.service;

import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.repo.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 *
 * @author FHHF
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(userName);
        
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));
        
        return user.map(UserDetailsImpl::new).get();
    }
    
}
