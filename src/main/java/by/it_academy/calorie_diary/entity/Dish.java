package by.it_academy.calorie_diary.entity;

import by.it_academy.calorie_diary.audit.AuditListener;
import by.it_academy.calorie_diary.audit.AuditableForType;
import by.it_academy.calorie_diary.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EntityListeners(AuditListener.class)
@AuditableForType(type = EssenceType.RECIPE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    @Id
    private UUID id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ElementCollection
    @OptimisticLock(excluded = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Composition> compositions = new ArrayList<>();
    @Column(name = "dt_create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime dateUpdate;
}
