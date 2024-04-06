package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.PersonFilterRequestDTO;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonFilterResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.model.Person;
import com.proyecto.b.s.rest.GenericResponse;
import com.proyecto.b.s.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /*@PostMapping("/create")
    public ResponseEntity<GenericResponse<PersonResponseDTO>> createPerson(
            @RequestBody @Valid PersonRequestDTO personRequestDTO, Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        try {
            if (StringUtils.isEmpty(personRequestDTO.getLinkedin()) && StringUtils.isEmpty(personRequestDTO.getEmail()))
            {
                throw new ResourceEntityNullException("LINKEDIN o CORREO");
            }

            return GenericResponse.createdResponse(objPersonResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }*/


    @PutMapping("/update")
    public ResponseEntity<GenericResponse<PersonResponseDTO>> updatePerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        try {
            PersonResponseDTO objPersonResponse = personService.updatePerson(personRequestDTO);
            return GenericResponse.acceptedResponse(objPersonResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<PersonResponseDTO>>> getAllPersons() {
        try {
            List<PersonResponseDTO> personResponseList = personService.findAllPerson();
            return GenericResponse.okResponse(personResponseList);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @PostMapping("/allbyfilter")
    public ResponseEntity<GenericResponse<PersonFilterResponseDTO>> getAllPersonsByFilter(
            @RequestBody PersonFilterRequestDTO personFilterRequestDTO) {
        try {
            PersonFilterResponseDTO personFilterResponseDTO = personService.findAllPersonsByFilter(personFilterRequestDTO);
            return GenericResponse.okResponse(personFilterResponseDTO);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/{personId}")
    public ResponseEntity<GenericResponse<PersonResponseDTO>> getPersonById(
            @PathVariable("personId") @NotNull Long personId) {
        try {
            PersonResponseDTO objPersonResponse = personService.findResponseById(personId);
            return GenericResponse.okResponse(objPersonResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/findbyname")
    public ResponseEntity<GenericResponse<List<Person>>> getPersonByNameAndLastName(@RequestParam("name") String name, @RequestParam("lastName") String lastName) {
        try {
            return GenericResponse.okResponse(personService.findByNameAndLastName(name, lastName));
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }
    @GetMapping("/findallbyname/{name}/{lastName}")
    public ResponseEntity <GenericResponse<List<Person>>> findAllByName(@PathVariable ("name") String name, @PathVariable ("lastName") String lastName){
        try{
            return GenericResponse.okResponse(personService.findAllByNameOrLastName(name, lastName));
        }catch( ResourceNotFoundException e){
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/findDuplicates")
    public ResponseEntity <GenericResponse<List<Person>>> findDuplicates(){
        try{
            return GenericResponse.okResponse(personService.findDuplicates());
        }catch( ResourceNotFoundException e){
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }


}
