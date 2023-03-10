package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Profile;
import by.it_academy.calorie_diary.services.api.IProfileService;
import by.it_academy.calorie_diary.services.dto.profile.ProfileRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final IProfileService service;

    public ProfileController(IProfileService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<Profile> createProfile(@Valid @RequestBody ProfileRequestDTO data) {
        Profile created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<Profile> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.read(id));
    }
}
