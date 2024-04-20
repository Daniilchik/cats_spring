package org.example.Entities;

import org.example.Enums.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catId;

    //private Integer ownerId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="owner_id", nullable = false)
    private Owner owner;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friendships",
            joinColumns = @JoinColumn(name="cat_id"),
            inverseJoinColumns = @JoinColumn(name="friend_id")
    )
    private List<Cat> friends;
}
