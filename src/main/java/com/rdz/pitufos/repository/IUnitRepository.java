package com.rdz.pitufos.repository;


import com.rdz.pitufos.domain.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnitRepository extends JpaRepository<UnitEntity,Long> {
}
