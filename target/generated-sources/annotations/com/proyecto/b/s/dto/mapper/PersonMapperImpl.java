package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonBasicResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.model.Person;
import com.proyecto.b.s.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T14:55:02-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person reqToPerson(PersonRequestDTO personRequestDTO) {
        if ( personRequestDTO == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( personRequestDTO.getId() );
        person.setName( personRequestDTO.getName() );
        person.setLastName( personRequestDTO.getLastName() );
        person.setContactDate( personRequestDTO.getContactDate() );
        person.setDni( personRequestDTO.getDni() );
        person.setEmail( personRequestDTO.getEmail() );
        person.setCuil( personRequestDTO.getCuil() );
        person.setPhoneNumber( personRequestDTO.getPhoneNumber() );
        person.setRemuneration( personRequestDTO.getRemuneration() );
        person.setObservations( personRequestDTO.getObservations() );

        return person;
    }

    @Override
    public PersonResponseDTO personToResp(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonResponseDTO personResponseDTO = new PersonResponseDTO();

        personResponseDTO.setUserResponseDTO( userToUserResponseDTO( person.getUser() ) );
        personResponseDTO.setId( person.getId() );
        personResponseDTO.setName( person.getName() );
        personResponseDTO.setLastName( person.getLastName() );
        personResponseDTO.setContactDate( person.getContactDate() );
        personResponseDTO.setDni( person.getDni() );
        personResponseDTO.setEmail( person.getEmail() );
        personResponseDTO.setCuil( person.getCuil() );
        personResponseDTO.setPhoneNumber( person.getPhoneNumber() );
        personResponseDTO.setRemuneration( person.getRemuneration() );
        personResponseDTO.setObservations( person.getObservations() );

        return personResponseDTO;
    }

    @Override
    public List<PersonResponseDTO> personToRespList(List<Person> personList) {
        if ( personList == null ) {
            return null;
        }

        List<PersonResponseDTO> list = new ArrayList<PersonResponseDTO>( personList.size() );
        for ( Person person : personList ) {
            list.add( personToResp( person ) );
        }

        return list;
    }

    @Override
    public PersonBasicResponseDTO personBasicToResp(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonBasicResponseDTO personBasicResponseDTO = new PersonBasicResponseDTO();

        personBasicResponseDTO.setId( person.getId() );
        personBasicResponseDTO.setName( person.getName() );
        personBasicResponseDTO.setLastName( person.getLastName() );
        personBasicResponseDTO.setEmail( person.getEmail() );

        return personBasicResponseDTO;
    }

    @Override
    public List<PersonBasicResponseDTO> personToBasicRespList(List<Person> personList) {
        if ( personList == null ) {
            return null;
        }

        List<PersonBasicResponseDTO> list = new ArrayList<PersonBasicResponseDTO>( personList.size() );
        for ( Person person : personList ) {
            list.add( personBasicToResp( person ) );
        }

        return list;
    }

    protected UserResponseDTO userToUserResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setPassword( user.getPassword() );
        userResponseDTO.setName( user.getName() );
        userResponseDTO.setLastName( user.getLastName() );
        userResponseDTO.setStatus( user.getStatus() );
        userResponseDTO.setToken( user.getToken() );
        userResponseDTO.setRole( user.getRole() );

        return userResponseDTO;
    }
}
