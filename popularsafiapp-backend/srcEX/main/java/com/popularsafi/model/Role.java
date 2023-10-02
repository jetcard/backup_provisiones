package com.popularsafi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TAB_ROL")
public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Integer C_ROL_ID;

    @Column(nullable = false, length = 50)
    private String D_NOMBRE;

    @Column(nullable = false, length = 100)
    private String C_ROL;


}
