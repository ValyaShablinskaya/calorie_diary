package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    private UUID id;
    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
}
