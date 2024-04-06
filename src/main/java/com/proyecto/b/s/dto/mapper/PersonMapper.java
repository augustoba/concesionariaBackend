package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.model.Person;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonBasicResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person reqToPerson(PersonRequestDTO personRequestDTO);


    @Mapping(source = "user", target = "userResponseDTO")
    PersonResponseDTO personToResp(Person person);

    List<PersonResponseDTO> personToRespList(List<Person> personList);

    PersonBasicResponseDTO personBasicToResp(Person person);

    List<PersonBasicResponseDTO> personToBasicRespList(List<Person> personList);
}
