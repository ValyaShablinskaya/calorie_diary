package by.it_academy.calorie_diary.entity;

import by.it_academy.calorie_diary.audit.AuditListener;
import by.it_academy.calorie_diary.audit.AuditableForType;
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
@AuditableForType(type = EssenceType.JOURNAL_FOOD)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "diary")
public class Diary implements Serializable {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "measure_of_weight")
    private MeasureOfWeight measureOfWeight;
    private Integer weight;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @Column(name = "dt_create")
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
}
