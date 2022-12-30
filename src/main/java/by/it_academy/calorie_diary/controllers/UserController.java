package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.services.api.IAuthenticationService;
import by.it_academy.calorie_diary.services.api.IUserService;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.user.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
private final IUserService service;
private final IAuthenticationService authenticationService;

    public UserController(IUserService service, IAuthenticationService authenticationService) {
        this.service = service;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO data) {
        UserDTO created = this.service.createUser(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("registration")
    protected ResponseEntity<UserDTO> registrationUser(@Valid @RequestBody UserRegistrationDTO data) {
        UserDTO created = this.service.registrationUser(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping("me")
    protected ResponseEntity<UserDTO> getInformationAboutCurrentUser() {
        return ResponseEntity.ok(service.getInfoAboutUser());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<PageDTO<UserDTO>> getListUsers(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }

    @PutMapping("/{id}/update_date/{update_date}")
    @PreAuthorize("hasAuthority('ADMIN')")
    protected ResponseEntity<UserDTO> updateUser(@PathVariable UUID id,
                                            @PathVariable("update_date") long updateTime,
                                            @RequestBody UserCreateDTO data) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateTime),
                ZoneId.of("UTC")
        );
        return ResponseEntity.ok(this.service.update(data, id, updateDate));
    }
    @PostMapping("/login")
    public JwtResponseDTO authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        return authenticationService.authenticate(userLoginDTO);
    }

    @PostMapping("/token/access")
    public JwtResponseDTO getNewAccessToken(@RequestBody RefreshJwtRequestDTO refreshJwtDto) {
        return authenticationService.getAccessToken(refreshJwtDto.getRefreshToken());
    }

    @PostMapping("/token/refresh")
    public JwtResponseDTO getNewRefreshToken(@RequestBody RefreshJwtRequestDTO refreshJwtDto) {
        return authenticationService.getRefreshToken(refreshJwtDto.getRefreshToken());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
    }
}
