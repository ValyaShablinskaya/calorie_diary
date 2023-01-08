package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profile")
public class Profile implements Serializable {
    @Id
    private UUID id;
    private Integer height;
    private Double weight;
    @Column(name = "dt_birthday")
    private LocalDate dateBirthday;
    private Double target;
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "dt_create")
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
}
