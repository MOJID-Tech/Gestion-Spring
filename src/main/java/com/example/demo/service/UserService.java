package com.example.demo.service;


import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.log.LogArgumentsAndResult;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
//import com.example.demo.utils.UtilisateurHelper;
//import com.example.demo.utils.UtilisateurHelper.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
//import com.example.demo.utils.MailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.security.ssl.KerberosClientKeyExchange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class UserService {


    private static final String LOGIN = "login";

    private static final String EMAIL = "email";

    //@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
   /* @Autowired
    private SalarieService salarieService;
    @Autowired
    private MailService mailService;*/


    @Autowired
    private UserRepository repository;
    @Autowired
    private user_roleService user_roleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ClientService clientService;

    @LogArgumentsAndResult
    @Transactional(readOnly = true)
    public Page<User> findAll(String pageIndex1, String size1) {
        System.out.println("heloousers");
        int pageIndex= Integer.parseInt(pageIndex1);
        int size= Integer.parseInt(size1);
        Pageable pageable = (Pageable) PageRequest.of(pageIndex,  size, Sort.Direction.DESC, "login");
        return repository.findAll(pageable);

    }

    @LogArgumentsAndResult
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        if (login == null) {
            throw new NullValueException(login);
        }
        User user = repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException(User.class, login, login));
        return user;
    }
    @LogArgumentsAndResult
    @Transactional(readOnly = true)
    public User  findById(Integer id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        User user;
        return user  = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

    }


    @LogArgumentsAndResult
    @Transactional
    public User authenticate(String login , String password ) {

        if (login== null) {
            throw new NullValueException("login");
        }
        if (password == null) {
            throw new NullValueException("password");
        }

        User userDto = findByLogin(login);

        try { authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,password));
            if (!userDto.isActive()) {
                throw new UserDeactivatedException();
            }
            if (userDto.getUser_Role().contains("ADMIN")) {
                throw new InsufficientRightException();
            }
            List<user_role> user_roles  ;
            user_roles = user_roleService.findbyUserId(userDto.getId());
            List<String>roles = new ArrayList<String>();
            for (user_role role : user_roles)
            {
                roles.add(role.getRole().getNomRole());
            }

            String token= jwtTokenProvider.createToken(login,roles);
            userDto.setToken(jwtTokenProvider.createToken(login, roles));

        } catch (NotFoundException e) {
            throw new NotFoundException(User.class, LOGIN, login);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException();
        }
        return userDto;
    }
    /*****************************/
   /* @Transactional(readOnly = false)
    public List<SelectionDTO> getUsersMinifiedList() {
        return repository.findByRoleAndActive(USER, true)
                .stream()
                .map(user -> SelectionDTO.builder().code(user.getId()).label(user.getFirstName() + " " + user.getLastName()).build())
                .collect(Collectors.toList());
    }*/

    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public User createUser(String login , String password , String email) {
        if (login == null) {
            throw new NullValueException(login);
        }
        if (password == null) {
            throw new NullValueException(password);
        }
        if (email == null) {
            throw new NullValueException(email);
        }
        String role;
        role = "User";
        /*
        if (repository.findByLogin(login).isPresent()) {
            throw new CredentialAlreadyExistsException("Login");
        }
       */
        // Client salarie = clientService.findbyEmail(email);
        System.out.println("salam");
        User user = new User();
        //   user.setSalarie(salarie);
        //user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);

        System.out.println("apres");

        user.setLogin(login.toLowerCase());

        user.setActive(true);
        User saveduser = repository.save(user);
        Role Role = roleService.finfbyNom("Prestataire");
        user_role user_role= user_roleService.create(Role ,saveduser);

        System.out.println("finally");

        return saveduser;

    }
    /*
    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public User updateUser(Integer id,  String login , String password) {
        if (id == null) {
            throw new NullValueException("id");
        }
        if (login == null) {
            throw new NullValueException("login");
        }

        User userToUpdate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        if (!userToUpdate.getLogin().equals(login) && repository.findByLogin(login).isPresent()) {
            throw new PropertyAlreadyUsedException(LOGIN);
        }
        userToUpdate.setLogin(login.toLowerCase());
       User userToUpdate2=repository.save(userToUpdate);


          if (passwordEncoder.matches(password, userToUpdate2.getPassword())){
              System.out.println("holaaa  Update");
            return (repository.save(userToUpdate));
        }
        else
        {
            //resetPassword(id,password);
            return (repository.save(userToUpdate));

        }


    }

    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public User addrole(Integer id,  String role) {
        if (id == null) {
            throw new NullValueException("id");
        }
        if (role == null) {
            throw new NullValueException("role");
        }
        Role Role = roleService.finfbyNom(role);
        User userToUpdate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        if (user_roleService.finfbyroleAndUser(Role.getId(),userToUpdate.getId())!=null) {
            throw new PropertyAlreadyUsedException("Role");
        }

        user_role user_role= user_roleService.create(Role ,userToUpdate );

        return (userToUpdate);
    }
    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public Boolean removerole(Integer id,  String role) {
        if (id == null) {
            throw new NullValueException("id");
        }
        if (role == null) {
            throw new NullValueException("role");
        }
        Role Role = roleService.finfbyNom(role);
        User userToUpdate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        if (user_roleService.finfbyroleAndUser(Role.getId(),userToUpdate.getId())==null) {
            throw new NotFoundException(user_role.class, "Role", role);
        }

        user_roleService.delet(user_roleService.finfbyroleAndUser(Role.getId(),userToUpdate.getId()).getId());
        return true;
    }


    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public User setActive(Integer id, String isActive1) {
        Integer active= Integer.parseInt(isActive1);
        System.out.println(active);
        Boolean Active;
        if(active.equals(0))
        {
            System.out.println("hello");
            Active=false;
        }
      else {  Active=true;}
        if (id == null) {
            throw new NullValueException("id");
        }
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        user.setActive(Active);
        return (repository.save(user));
    }

    @LogArgumentsAndResult
    @Transactional(readOnly = false)
    public User changePassword(String login, String currentPassword, String newPassword) {
        if (login == null) {
            throw new InsufficientRightException();
        }
        if (currentPassword == null) {
            throw new NullValueException("currentPassword");
        }
        if (newPassword == null) {
            throw new NullValueException("newPassword");
        }
        User user = repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException(User.class, login, login));
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            User savedUser = repository.save(user);
            return (savedUser);
        } else {
            throw new WrongPasswordException();
        }

    }

    @LogArgumentsAndResult
    @Transactional
    public ValidationEmailDTO resetPassword(Integer id, String newPassword) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        Long id_salarie = repository.findSalarie(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));

        Salarie salarie = salarieService.finfbyid(id_salarie);
        String email= salarie.getEmailsalarie();
        System.out.println("*******************"+email+"***************************");
        boolean checkEmailSend = mailService.send(MailType.RESET_PASSWORD, email, new String[]{newPassword});
        if (!checkEmailSend) {
            throw new SendEmailException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        return ValidationEmailDTO.builder().code(200).message("Veuillez vérifier votre boite email").build();
    }

    @LogArgumentsAndResult
    @Transactional

    public ValidationEmailDTO resetPassword(String email , String login ) {
        Salarie salarie = salarieService.findbyEmail(email);
       /* User utilisateur = repository.findBySalarieId(salarie.getId())
                .orElseThrow(() -> new EmailNotFoundException());*/

  /*User utilisateur = repository.findBySalarieIdAndLogin(salarie.getId() , login)
                .orElseThrow(() -> new EmailNotFoundException());


        String newPassword = UtilisateurHelper.generateRandomPassword();
        boolean checkEmailSend = mailService.send(MailType.RESET_PASSWORD, email, new String[]{newPassword});
        if (!checkEmailSend) {
            throw new SendEmailException();
        }
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        repository.save(utilisateur);
        return ValidationEmailDTO.builder().code(200).message("Veuillez vérifier votre boite email").build();

    }

    @LogArgumentsAndResult
    @Transactional
    public List<String> getroles (Integer id)
    {
        User user = this.findById(id);
        List<user_role> user_roles  ;
        user_roles = user_roleService.findbyUserId(user.getId());
        List<String>roles = new ArrayList<String>();
        for (user_role role : user_roles)
        {
            roles.add(role.getRole().getNomRole());
        }
        return roles;
    }*/



}

