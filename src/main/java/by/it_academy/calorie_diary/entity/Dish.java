package by.it_academy.calorie_diary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    @Id
    private UUID id;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Composition> compositions = new ArrayList<>();
    @Column(name = "dt_create")
    private LocalDateTime dateCrete;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
}
