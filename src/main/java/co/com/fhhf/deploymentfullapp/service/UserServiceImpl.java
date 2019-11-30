package co.com.fhhf.deploymentfullapp.service;

import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.repo.UserRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author FHHF
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findByName(String userName) {
        try{
            Optional<User> user = userRepo.findByUserName(userName);
            return user.get();
        }catch (Throwable unf){
            User userE = new User();
            userE.setUserName("");
            return userE;
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
}
