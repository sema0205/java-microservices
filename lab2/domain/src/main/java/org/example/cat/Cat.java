package org.example.cat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.owner.Owner;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cat")
@Getter
@Setter
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "birthdate")
    private LocalDateTime birthDate;

    @Enumerated(value = EnumType.STRING)
    private Breed breed;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cat_owner_item",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private Owner owner;

    @ElementCollection
    @CollectionTable(
            name = "cat_friend_item",
            joinColumns = @JoinColumn(name = "cat_id")
    )
    @Column(name = "friend_cat_id")
    private List<Long> friendIds;

}