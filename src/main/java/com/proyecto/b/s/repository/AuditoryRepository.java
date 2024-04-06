package com.proyecto.b.s.repository;

import com.proyecto.b.s.model.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
}
