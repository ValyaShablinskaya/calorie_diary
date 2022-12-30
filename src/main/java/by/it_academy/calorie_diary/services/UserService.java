package by.it_academy.calorie_diary.services;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.entity.UserRole;
import by.it_academy.calorie_diary.entity.UserStatus;
import by.it_academy.calorie_diary.mappers.IUserMapper;
import by.it_academy.calorie_diary.repository.IUserRepository;
import by.it_academy.calorie_diary.services.api.IUserService;
import by.it_academy.calorie_diary.services.dto.*;
import by.it_academy.calorie_diary.services.dto.user.UserCreateDTO;
import by.it_academy.calorie_diary.services.dto.user.UserDTO;
import by.it_academy.calorie_diary.services.dto.user.UserRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String ENTITY_NOT_FOUND_EXCEPTION = "User is not found";
    private static final String USER_ALREADY_EXISTS_EXCEPTION = "Specified user already exists";

    public UserService(IUserRepository repository, IUserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO item) {
        String email = item.getMail();
        if (isUserExistByEmail(email)) {
            throw new EntityExistsException(USER_ALREADY_EXISTS_EXCEPTION);
        }

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setDateCrete(LocalDateTime.now());
        user.setDateUpdate(user.getDateCrete());

        user.setNick(item.getNick());
        user.setMail(item.getMail());
        user.setPassword(passwordEncoder.encode(item.getPassword()));
        user.setStatus(item.getStatus());
        user.setRole(item.getRole());
        user = repository.save(user);

        return userMapper.convertToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO registrationUser(UserRegistrationDTO item) {
        String email = item.getMail();
        if (isUserExistByEmail(email)) {
            throw new EntityExistsException(USER_ALREADY_EXISTS_EXCEPTION);
        }

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setDateCrete(LocalDateTime.now());
        user.setDateUpdate(user.getDateCrete());

        user.setMail(item.getMail());
        user.setNick(item.getNick());
        user.setPassword(passwordEncoder.encode(item.getPassword()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVATED);
        user = repository.save(user);

        return userMapper.convertToDTO(user);
    }

    @Override
    public UserDTO read(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return userMapper.convertToDTO(user);
    }

    @Override
    public PageDTO<UserDTO> get(Pageable pageable) {
        Page<User> content = repository.findAll(pageable);

        PageDTO<UserDTO> pageDTO = new PageDTO();
        pageDTO.setNumber(content.getNumber());
        pageDTO.setSize(content.getSize());
        pageDTO.setTotalPages(content.getTotalPages());
        pageDTO.setTotalElements(content.getTotalElements());
        pageDTO.setFirst(content.isFirst());
        pageDTO.setNumberOfElements(content.getNumberOfElements());
        pageDTO.setLast(content.isLast());
        pageDTO.setContent(userMapper.convertToListDTO(content.getContent()));
        return pageDTO;
    }

    @Override
    @Transactional
    public UserDTO update(UserCreateDTO item, UUID id, LocalDateTime updateData) {
        User read = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!read.getDateUpdate().isEqual(updateData)) {
            throw new IllegalArgumentException("User has been already edited");
        }
        read.setDateUpdate(LocalDateTime.now());
        read.setMail(item.getMail());
        read.setNick(item.getNick());
        read.setRole(item.getRole());
        read.setStatus(item.getStatus());
        read.setPassword(passwordEncoder.encode(item.getPassword()));
        read = repository.save(read);

        return userMapper.convertToDTO(read);
    }
    @Override
    public UserDTO getInfoAboutUser(){
        String mail = findCurrentUser();
        User user = repository.findByMail(mail).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return userMapper.convertToDTO(user);
    }

    private boolean isUserExistByEmail(String mail) {
        return repository.findByMail(mail).isPresent();
    }

    private String findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}