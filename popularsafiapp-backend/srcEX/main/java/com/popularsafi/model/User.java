package com.popularsafi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TAB_USUARIO")
public class User {

    @Id
    @Column(nullable = false, length = 60, unique = true)
    private String C_USUARIO_ID;

    @Column(nullable = false, length = 60)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TAB_USUARIO_ROL",
            joinColumns = @JoinColumn(name = "C_USUARIO_ID", referencedColumnName = "C_USUARIO_ID"),
            inverseJoinColumns = @JoinColumn(name = "C_ROL_ID", referencedColumnName = "C_ROL_ID")
    )
    private List<Role> roles;
}
