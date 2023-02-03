package com.aster.app.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aster.app.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
