package org.example.owner;

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

    private LocalDateTime birthDate;

    @Column(name = "cat")
    @CollectionTable(name = "cat_owner_item")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Cat> cats;

}
