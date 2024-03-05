package com.rdz.pitufos.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "bill")
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean status;
    private BigDecimal total;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate date;

    @OneToMany(targetEntity = BillDetailEntity.class,mappedBy = "bill")
    List<BillDetailEntity> orders;
}
