package com.aclti.academy.repositories;

import com.aclti.academy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository interface
 * @author Alexander Schilling
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
