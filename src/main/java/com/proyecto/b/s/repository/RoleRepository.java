package com.proyecto.b.s.repository;


import com.proyecto.b.s.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    List<Role> findAllByOrderByName();

}
