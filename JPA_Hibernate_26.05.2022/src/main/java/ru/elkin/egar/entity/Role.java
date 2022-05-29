package ru.elkin.egar.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "role")
@NamedQuery(
        name = "find_all_roles",
        query = "SELECT r FROM Role r"
)
@NamedEntityGraph(
        name = "get_users_by_role",
        attributeNodes = @NamedAttributeNode("users"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_text")
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    @ToString.Exclude
    private Set<User> users;

}
