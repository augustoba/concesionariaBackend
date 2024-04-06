package com.proyecto.b.s.repository;

import com.proyecto.b.s.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String name, String lastName);

    List<Person> findAllByOrderByName();

    Person findByName(String name);


    List<Person> findByDni(String dni);
    List<Person> findByPhoneNumber(String phoneNumber);
    List<Person> findByEmail(String email);

    Person findByNameAndLastName(String name, String lastName);

    @Query("SELECT p FROM Person p " +
            "WHERE TRIM(p.name) = TRIM(:name)" +
            "AND TRIM(p.lastName) = TRIM(:lastName) "
    )
    List<Person> findAllByNameAndLastName(String name, String lastName);



    @Query("SELECT p FROM Person p " +
            "WHERE p.name = :name " +
            "AND p.lastName = :lastName " +
            "AND (:dni IS NULL OR p.dni = :dni) " +
            "AND (:email IS NULL OR p.email = :email) " +
            "AND (:cuil IS NULL OR p.cuil = :cuil) " +
            "AND (:phoneNumber IS NULL OR p.phoneNumber = :phoneNumber)" +
            "AND (:phoneNumber IS NULL OR p.phoneNumber = :phoneNumber)"
    )
    Person findPerson(String name, String lastName ,String dni, String email, String cuil, String phoneNumber);

    List<Person> findPersonListByIdIn(List<Long> personIds);

    @Query("SELECT DISTINCT p FROM Person p " +
            "WHERE (TRIM(:name) IS NULL OR TRIM(p.name) LIKE CONCAT('%', TRIM(:name), '%')) " +
            "AND (TRIM(:lastName) IS NULL OR TRIM(p.lastName) LIKE CONCAT('%', TRIM(:lastName), '%')) " +
             "AND (:userId IS NULL OR p.user.id = :userId) ")

    Page<Person> findAllByFilter(@Param("name") String name, @Param("lastName") String lastName,
                                 @Param("userId") Long userId,Pageable pageable);


    List<Person> findAllByOrderByContactDateDesc(Pageable pageable);

    @Query("SELECT p FROM Person p WHERE (p.name, p.lastName) IN (SELECT p2.name, p2.lastName FROM Person p2 GROUP BY p2.name, p2.lastName HAVING COUNT(*) > 1)")
    List<Person> findDuplicatePersons();



}



