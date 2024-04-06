package com.proyecto.b.s.service;

import com.proyecto.b.s.dto.request.PersonFilterRequestDTO;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonFilterResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.model.Person;
import com.proyecto.b.s.security.UserPrincipal;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PersonService {

    @Transactional
    Person createPerson(PersonRequestDTO personRequestDTO, UserPrincipal userPrincipal) throws ResourceNotFoundException;

    List<Person> findByNameAndLastName(String name, String lastName) throws ResourceNotFoundException;

    List<PersonResponseDTO> findAllPerson() throws ResourceNotFoundException;

    List<Person> findAllPersonEntity() throws ResourceNotFoundException;

    PersonFilterResponseDTO findAllPersonsByFilter(PersonFilterRequestDTO personFilterRequestDTO) throws ResourceNotFoundException;

    @Transactional
    PersonResponseDTO updatePerson(PersonRequestDTO personRequestDTO) throws ResourceNotFoundException;

    Person findById(Long personId) throws ResourceNotFoundException;

    PersonResponseDTO findResponseById(Long personId) throws ResourceNotFoundException;

    @Transactional
    void savePerson(Person person);


    List<Person> findAllByNameOrLastName(String name, String lastName) throws ResourceNotFoundException;


    List<Person> findDuplicates() throws ResourceNotFoundException;

}
