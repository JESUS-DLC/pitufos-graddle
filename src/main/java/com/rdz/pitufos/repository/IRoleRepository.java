package com.rdz.pitufos.repository;


import com.rdz.pitufos.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
}
