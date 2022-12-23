package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String title;
    private int calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    @Enumerated(EnumType.STRING)
    private MeasureOfWeight measureOfWeight;
    private int weight;
    @Column(name = "dt_create")
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
}
