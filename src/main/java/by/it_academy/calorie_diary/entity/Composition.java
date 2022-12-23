package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Composition implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(name = "measure_of_weight")
    private MeasureOfWeight measureOfWeight;
    private int weigh;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
