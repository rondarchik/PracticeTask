package org.system.creditmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @ManyToMany
    @JoinTable(name = "user_role",
               joinColumns = {@JoinColumn(name = "id_role")},
               inverseJoinColumns = {@JoinColumn(name = "id_user")})
    @JsonBackReference
    private List<User> users;
}
