package org.example.owner;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.cat.Cat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;

    @ElementCollection
    @CollectionTable(
            name = "cat_owner_item",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Column(name = "cat_id")
    private List<Long> CatIds;

}
