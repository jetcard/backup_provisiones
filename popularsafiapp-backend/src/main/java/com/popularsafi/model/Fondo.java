package com.popularsafi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Fondo {
    @Id
    @Column(nullable = false, length = 4, unique = true)
    private String C_FONDO_ID;

    @Column(nullable = false, length = 200)
    private String D_FONDO;

}
