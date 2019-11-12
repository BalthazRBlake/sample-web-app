package co.com.fhhf.deploymentfullapp.service;

import co.com.fhhf.deploymentfullapp.model.User;

import java.util.Optional;

/**
 *
 * @author FHHF
 */
public interface UserService {
    
    public User findByName(String userName);
   
    public void saveUser(User user);
}
