package com.proyecto.b.s.service.service_impl;

import com.proyecto.b.s.dto.mapper.UserMapper;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.exception.ResourceAlreadyExistsException;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.security.jwt.JwtProvider;
import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.request.UserUpdateRequestDTO;
import com.proyecto.b.s.dto.response.UserBasicResponseDTO;
import com.proyecto.b.s.exception.ResourceEntityNullException;
import com.proyecto.b.s.model.User;
import com.proyecto.b.s.service.RoleService;
import com.proyecto.b.s.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserMapper userMapper,
                           PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }
    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws ResourceNotFoundException {
        if (userRequestDTO == null) {
            throw new ResourceEntityNullException("Usuario");
        }
        User objActualUser = userRepository.findByEmail(userRequestDTO.getEmail());

        if (objActualUser != null) {
            if (!objActualUser.getStatus()) {
                throw new ResourceAlreadyExistsException("existDBStatus", "Usuario");
            } else {
                throw new ResourceAlreadyExistsException("existDB", "Usuario");
            }
        }
        User objUser = userMapper.reqToUser(userRequestDTO);
        objUser.setStatus(true);
        objUser.setPassword(passwordEncoder.encode("Gyl1234@"));
        objUser.setRole(roleService.findRoleById(userRequestDTO.getRoleId()));
        userRepository.save(objUser);

        return userMapper.userToResp(objUser);
    }
    @Transactional
    @Override
    public void changeStatus(Long userId) throws ResourceNotFoundException {
        if (userId == null) {
            throw new ResourceEntityNullException("Usuario");
        }
        Optional<User> objAux = userRepository.findById(userId);

        if (objAux.isPresent()) {
            User objUser = objAux.get();
            boolean status = objUser.getStatus();
            objUser.setStatus(!status);
            userRepository.save(objUser);
        } else {
            throw new ResourceNotFoundException("noExistDB", "Usuario");
        }

    }

    @Override
    public List<UserResponseDTO> findAllByStatusTrue() throws ResourceNotFoundException {
        List<User> userList = userRepository.findAllByStatusTrueOrderByName();
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }
        return userMapper.userToRespList(userList);
    }
    @Transactional
    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) throws ResourceNotFoundException {
        if (userUpdateRequestDTO == null || userUpdateRequestDTO.getId() == null) {
            throw new ResourceEntityNullException("Usuario");
        }

        User objUser = userRepository.findById(userUpdateRequestDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario"));

        if (userUpdateRequestDTO.getEmail() != null) {
            objUser.setEmail(userUpdateRequestDTO.getEmail());
        }

        if (userUpdateRequestDTO.getPassword() != null && !userUpdateRequestDTO.getPassword().trim().isEmpty()) {
            objUser.setPassword(passwordEncoder.encode(userUpdateRequestDTO.getPassword()));
        }

        if (userUpdateRequestDTO.getName() != null) {
            objUser.setName(userUpdateRequestDTO.getName());
        }

        if (userUpdateRequestDTO.getLastName() != null) {
            objUser.setLastName(userUpdateRequestDTO.getLastName());
        }

        if (userUpdateRequestDTO.getStatus() != null) {
            objUser.setStatus(userUpdateRequestDTO.getStatus());
        }

        if (userUpdateRequestDTO.getRoleId() != null) {
            objUser.setRole(roleService.findRoleById(userUpdateRequestDTO.getRoleId()));
        }

        userRepository.save(objUser);
        return userMapper.userToResp(objUser);
    }

    @Override
    public List<UserBasicResponseDTO> findAllUser() throws ResourceNotFoundException {
        List<User> userList = userRepository.findAllByOrderByName();
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }
        return userMapper.userToBasicRespList(userList);
    }
    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findUserByEmailAndStatusIsTrue(email);
    }

    @Override
    public UserResponseDTO findUserNameReturnToken(String username){
        User user = userRepository.findUserByEmailAndStatusIsTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe " + username));

        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);

        return userMapper.userToResp(user);
    }

    @Override
    public User findById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario"));
    }

    @Override
    public UserBasicResponseDTO findUserResponseById(Long searchId) throws ResourceNotFoundException {
        return userMapper.userToBasicResp(userRepository.findById(searchId)
                .orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario")));
    }

    @Transactional
    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public void changeUserPassword(String email, String password) throws ResourceNotFoundException {
        User objUser = findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("noExistDB", "Usuario"));
        objUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(objUser);
    }

    @Override
    public User usuarios(String excelImport)  {

        String[] nameParts = excelImport.split(",");

        String lastName="";
        String firstName="";
        User objUser ;

        if (nameParts.length == 2) {
             lastName = nameParts[0].trim();
             firstName = nameParts[1].trim();
            objUser = userRepository.findByNameAndLastName(firstName, lastName);

        }else{
            firstName=excelImport;

            objUser = userRepository.findByName(firstName);
        }

        /*//if (objUser == null){
            objUser= new User();
            objUser.setLastName(lastName);
            objUser.setName(firstName);
            userRepository.save(objUser);
        }*/
        return objUser;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User userList(String excelImportSearch)  {


        String[] nameParts = excelImportSearch.split(",");

        String lastName="";
        String firstName="";
        User objUser ;

        if (nameParts.length == 2) {
            lastName = nameParts[0].trim();
            firstName = nameParts[1].trim();
            objUser = userRepository.findByNameAndLastName(firstName, lastName);

        }else{
            firstName= excelImportSearch;
            objUser = userRepository.findByName(lastName);
        }

        return objUser;
    }
}
