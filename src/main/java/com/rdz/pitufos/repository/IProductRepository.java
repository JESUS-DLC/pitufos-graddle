package com.rdz.pitufos.repository;


import com.rdz.pitufos.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductEntity,Long> {

    //List<ProductEntity> findAllByStatusTrue();

    List<ProductEntity> findAllByOrderByName();
    List<ProductEntity> findByStatusTrueOrderByName();
    //List<ProductEntity> findAllByStatusTrueOrderedByName();
}
