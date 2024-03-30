package org.example.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cat")
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private DateTime birthDate;

    private Breed breed;
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owner owner;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Cat> friends = new ArrayList<>();

    public void addFriend(Cat friend) {
        friends.add(friend);
    }

}
