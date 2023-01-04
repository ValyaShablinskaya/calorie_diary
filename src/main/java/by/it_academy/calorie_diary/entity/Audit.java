package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "audit")
public class Audit implements Serializable {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String text;
    @Enumerated(EnumType.STRING)
    private EssenceType type;
}
