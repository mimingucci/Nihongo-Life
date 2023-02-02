package com.nihongo.admin.user;

import com.nihongo.admin.paging.SearchRepository;
import com.nihongo.common.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserByEmail(String email);

    @Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
    @Modifying
    public void updateEnable(Integer id, Boolean enabled);

    @Query("SELECT u FROM User u WHERE CONCAT(u.email,' ', u.firstName,' ', u.lastName) LIKE %?1%")
    public Page<User> listUsersByPage(String keyword, Pageable pageable);

    public Long countById(Integer id);

    @Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
    @Modifying
    void updateEnabledStatus(Integer id, boolean enabled);

    @Query("SELECT u FROM User u WHERE u.email= ?1")
    public User findByEmail(String email);

    public List<User> findAll(Sort firstName);
}
