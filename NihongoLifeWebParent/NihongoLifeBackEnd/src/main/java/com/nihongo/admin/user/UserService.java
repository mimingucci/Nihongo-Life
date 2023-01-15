package com.nihongo.admin.user;

import com.nihongo.admin.paging.PagingAndSortingHelper;
import com.nihongo.admin.paging.SearchRepository;
import com.nihongo.common.entity.PagingDTO;
import com.nihongo.common.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    public static final int USER_PER_PAGE=10;
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void newUser(User user) throws UserNotFoundException {
        User savedUser=repo.findUserByEmail(user.getEmail());
        if(savedUser==null){
            repo.save(user);
        }else{
            throw new UserNotFoundException("Email "+user.getEmail()+" was used with an other account");
        }
    }

    public String deleteUser(Integer id) throws UserNotFoundException {
        User targetUser=repo.findById(id).get();
        if(targetUser!=null){
            repo.deleteById(id);
            return "User with id "+id+" has been deleted";
        }else{
            throw new UserNotFoundException("There isn't any user with id "+id);
        }
    }

    public PagingDTO listByPage(int pageNum, PagingAndSortingHelper helper){
        return helper.listEntities(pageNum, USER_PER_PAGE, (SearchRepository<User, Integer>) repo);
    }

    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        repo.updateEnabledStatus(id, enabled);
    }

    public User get(Integer id) throws UserNotFoundException{
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find any customers with ID " + id);
        }
    }

    public boolean isEmailUnique(Integer id, String email) {
        User existUser = repo.findByEmail(email);

        if (existUser != null && existUser.getId() != id) {
            // found another customer having the same email
            return false;
        }

        return true;
    }

    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if (isUpdatingUser) {
            User existingUser = repo.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }

        } else {
            encodePassword(user);
        }

        return repo.save(user);
    }
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public User updateAccount(User userInForm) {
        User userInDB = repo.findById(userInForm.getId()).get();

        if (!userInForm.getPassword().isEmpty()) {
            userInDB.setPassword(userInForm.getPassword());
            encodePassword(userInDB);
        }

        if (userInForm.getPhotos() != null) {
            userInDB.setPhotos(userInForm.getPhotos());
        }

        userInDB.setFirstName(userInForm.getFirstName());
        userInDB.setLastName(userInForm.getLastName());

        return repo.save(userInDB);
    }

    public User getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<User> listAll() {
        return (List<User>) repo.findAll(Sort.by("firstName").ascending());
    }
}
