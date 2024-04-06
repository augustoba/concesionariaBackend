package com.proyecto.b.s.repository;

import com.proyecto.b.s.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    User findByEmail(String email);
    List<User> findAllByOrderByName();
    List<User> findAllByStatusTrueOrderByName();
    Optional<User> findUserByEmailAndStatusIsTrue(String email);
    User findByNameAndLastName(String name, String lastName);
}
