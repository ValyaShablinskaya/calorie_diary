package by.it_academy.calorie_diary.entity;

import by.it_academy.calorie_diary.audit.AuditListener;
import by.it_academy.calorie_diary.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EntityListeners(AuditListener.class)
@Auditable(type = EssenceType.USER)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private UUID id;
    private String mail;
    private String nick;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String password;
    @Column(name = "dt_create")
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
}
