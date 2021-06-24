package org.simbirsoft.kokutov.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 80)
    @EqualsAndHashCode.Include
    private String username;

    @Column(nullable = false)
    @Size(min = 6, max = 30)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

}
