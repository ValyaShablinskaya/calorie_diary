package by.it_academy.calorie_diary.entity;

import by.it_academy.calorie_diary.audit.AuditListener;
import by.it_academy.calorie_diary.audit.AuditableForType;
import by.it_academy.calorie_diary.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
@AuditableForType(type = EssenceType.PRODUCT)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    private UUID id;
    private String title;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    @Enumerated(EnumType.STRING)
    private MeasureOfWeight measureOfWeight;
    private Integer weight;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
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
