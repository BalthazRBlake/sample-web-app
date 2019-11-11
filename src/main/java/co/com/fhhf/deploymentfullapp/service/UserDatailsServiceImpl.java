package co.com.fhhf.deploymentfullapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 *
 * @author FHHF
 */

@Service
public class UserDatailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return new UserDetailsImpl(user);
    }
    
}
