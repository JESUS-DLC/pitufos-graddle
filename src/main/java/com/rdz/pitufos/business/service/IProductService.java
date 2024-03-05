package com.rdz.pitufos.business.service;


import com.rdz.pitufos.domain.entity.ProductEntity;

import java.util.List;
import java.util.Optional;


public interface IProductService {

    List<ProductEntity> findAll();

    boolean existsById(long id);

    Optional<ProductEntity> findById(long id);

    ProductEntity save(ProductEntity productEntity);

    void delete(Long id);

    List<ProductEntity> findAllActive();
}
