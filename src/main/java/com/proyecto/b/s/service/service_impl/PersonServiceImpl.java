package com.proyecto.b.s.service.service_impl;

import com.proyecto.b.s.dto.mapper.PersonMapper;
import com.proyecto.b.s.dto.request.PersonFilterRequestDTO;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonBasicResponseDTO;
import com.proyecto.b.s.dto.response.PersonFilterResponseDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.exception.ResourceAlreadyExistsException;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.model.Person;
import com.proyecto.b.s.model.User;
import com.proyecto.b.s.security.UserPrincipal;
import com.proyecto.b.s.service.AuditoryService;
import com.proyecto.b.s.service.PersonService;
import com.proyecto.b.s.service.UserService;
import com.proyecto.b.s.util.CountPage;
import com.proyecto.b.s.exception.ResourceEntityNullException;
import com.proyecto.b.s.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    private final UserService userService;


    private final AuditoryService auditoryService;

    public PersonServiceImpl(PersonMapper personMapper, PersonRepository personRepository,
                              UserService userService,AuditoryService auditoryService) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.userService = userService;
        this.auditoryService = auditoryService;
    }

    @Override
    @Transactional
    public Person createPerson(PersonRequestDTO personRequestDTO, UserPrincipal userPrincipal) throws ResourceNotFoundException {

        personRequestValidate(personRequestDTO);

        String username = userPrincipal.getUsername();
        User objUser = userService.findByEmail(username).orElseThrow(() -> new ResourceEntityNullException("Persona"));


        Person objPerson = personMapper.reqToPerson(personRequestDTO);
        String linkedin= personRequestDTO.getLinkedin();

        objPerson.setUser(objUser);

        if (personRequestDTO.getCuil() != null) {
            objPerson.setCuil(personRequestDTO.getCuil());
        } else {
            objPerson.setCuil(" ");        }



        if (personRequestDTO.getEmail() != null) {
            objPerson.setEmail(personRequestDTO.getEmail());
        } else {
            objPerson.setEmail(" ");
        }

        if (personRequestDTO.getPhoneNumber() != null) {
            objPerson.setPhoneNumber(personRequestDTO.getPhoneNumber());
        } else {
            objPerson.setPhoneNumber(" ");
        }

        if (personRequestDTO.getRemuneration() != null) {
            objPerson.setRemuneration(personRequestDTO.getRemuneration());
        } else {
            objPerson.setRemuneration("0");
        }

        if (personRequestDTO.getObservations() != null) {
            objPerson.setObservations(personRequestDTO.getObservations());
        } else {
            objPerson.setObservations(" ");
        }

        personRepository.save(objPerson);

        auditoryService.auditorySave(personTemplate(objPerson),"no hay evento anterior",objUser,"create person");


        return objPerson;
    }

    private void personRequestValidate(PersonRequestDTO personRequestDTO) {
        if (personRequestDTO == null) {
            throw new ResourceEntityNullException("Persona");      }

        if ((!personRepository.findByDni(personRequestDTO.getDni()).isEmpty()) && (!personRequestDTO.getDni().isEmpty())) {
            throw new ResourceAlreadyExistsException("existDB", "Persona");
        }

        if ((!personRepository.findByPhoneNumber(personRequestDTO.getPhoneNumber()).isEmpty()) && (!personRequestDTO.getPhoneNumber().isEmpty())) {
            throw new ResourceAlreadyExistsException("existDB", "Persona");
        }

        if ((!personRepository.findByEmail(personRequestDTO.getEmail()).isEmpty()) && (!personRequestDTO.getEmail().isEmpty())) {
            throw new ResourceAlreadyExistsException("existDB", "Persona");
        }
    }


    @Override
    public List<Person> findByNameAndLastName(String name, String lastName) throws ResourceNotFoundException {

        List<Person> personList= personRepository.findAllByNameAndLastName(name,lastName);


        if (personList.isEmpty() ) {
            throw new ResourceNotFoundException("noExistDB", "Persona");
        }
        return personList;
    }

    @Override
    public List<PersonResponseDTO> findAllPerson() throws ResourceNotFoundException {
        List<Person> personList = personRepository.findAllByOrderByName();

        if (personList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }
        return personMapper.personToRespList(personList);
    }

    @Override
    public List<Person> findAllPersonEntity() throws ResourceNotFoundException {
        List<Person> personList = personRepository.findAllByOrderByName();

        if (personList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }
        return personList;
    }

    @Override
    public PersonFilterResponseDTO findAllPersonsByFilter(PersonFilterRequestDTO personFilterRequestDTO) throws ResourceNotFoundException {
        if (personFilterRequestDTO == null) {
            throw new ResourceEntityNullException("Persona");
        }

        String name = personFilterRequestDTO.getName();
        String lastName = personFilterRequestDTO.getLastName();

        Pageable pageable = PageRequest.of(personFilterRequestDTO.getPage() - 1, 10);
        Page<Person> personPage = personRepository.findAllByFilter(name, lastName, personFilterRequestDTO.getUserId(), pageable);

        Long countPage = CountPage.countPages(personPage.getTotalElements());
        List<Person> personList = personPage.getContent();

        if (personList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");        }

        List<PersonBasicResponseDTO> personResponseDTOList = personMapper.personToBasicRespList(personList);

        PersonFilterResponseDTO personFilterResponseDTO = new PersonFilterResponseDTO();
        personFilterResponseDTO.setCountPage(countPage);
        personFilterResponseDTO.setPersonResponseDTOList(personResponseDTOList);

        return personFilterResponseDTO;
    }

    @Override
    @Transactional
    public PersonResponseDTO updatePerson(PersonRequestDTO personRequestDTO) throws ResourceNotFoundException {
        if (personRequestDTO == null || personRequestDTO.getId() == null) {
            throw new ResourceEntityNullException("Persona");
        }

        Optional<Person> objAux = personRepository.findById(personRequestDTO.getId());

        String personBefore= null;

        if (objAux.isPresent()) {
            Person objPerson = objAux.get();
            personBefore=personTemplate(objPerson);
            objPerson.setName(personRequestDTO.getName());
            objPerson.setLastName(personRequestDTO.getLastName());

            String linkedin= personRequestDTO.getLinkedin();

            objPerson.setCuil(personRequestDTO.getCuil());
            objPerson.setDni(personRequestDTO.getDni());
            objPerson.setRemuneration(personRequestDTO.getRemuneration());
            objPerson.setEmail(personRequestDTO.getEmail());
            objPerson.setPhoneNumber(personRequestDTO.getPhoneNumber());
            objPerson.setContactDate(personRequestDTO.getContactDate());
            objPerson.setObservations(personRequestDTO.getObservations());

            personRepository.save(objPerson);
            User user = userService.findById(personRequestDTO.getUserId());

            auditoryService.auditorySave(personTemplate(objPerson),personBefore,user,"Update Person");

            return personMapper.personToResp(objPerson);
        } else {
            throw new ResourceNotFoundException("noExistDB", "Persona");
        }
    }

    @Override
    public Person findById(Long personId) throws ResourceNotFoundException {
        return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Persona"));
    }

    @Override
    public PersonResponseDTO findResponseById(Long personId) throws ResourceNotFoundException {
        return personMapper.personToResp(personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Persona")));
    }

    @Transactional
    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }



    /*public Person findByNameAndLastNameExcel(ExcelImport excelImport) {

        Person person = personRepository.findByNameAndLastName(excelImport.getNombreCandidato(), excelImport.getApellidoCandidato());

        if (person == null) {
            person = new Person();
            person.setLastName(excelImport.getApellidoCandidato());
            person.setName(excelImport.getNombreCandidato());
            person.setUser(userService.usuarios(excelImport.getEntrevistador()));
            String dateString = excelImport.getFechaRegistro();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            person.setContactDate(date);
            personRepository.save(person);

        }
        return person;
    }
*/
  /*  @Override
    public Person personExcel(Long idPerson) {
        Person personReturn = null;
        Optional<Person> person = personRepository.findById(idPerson);
        if (person.isPresent()) {
            personReturn = person.get();
        }
        return personReturn;
    }*/

    /*@Override
    public Person findByExcelId(Long id) {
        return personRepository.findByExcelId(id);
    }*/

    @Override
    public List<Person> findAllByNameOrLastName(String name, String lastName) throws ResourceNotFoundException {
        List<Person> objPersonList = personRepository.findAllByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(name, lastName);

        if (objPersonList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }

        return objPersonList;
    }


    @Override
    public List<Person> findDuplicates() throws ResourceNotFoundException {
        List<Person> duplicates = personRepository.findDuplicatePersons();
        return duplicates;
    }


    private String personTemplate(Person person){

        return " -id person = " + person.getId()  + " -person name = " + person.getName()+ " " + person.getLastName()+
                " -dni =" + person.getDni() + " -remuneration = " + person.getRemuneration() +
                " -mail = " + person.getEmail() + " - phone number = " + person.getPhoneNumber()+
                " - observation = " + person.getObservations() ;
 }

}