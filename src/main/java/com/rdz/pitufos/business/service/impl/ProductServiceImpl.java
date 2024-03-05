package com.rdz.pitufos.business.service.impl;


import com.rdz.pitufos.business.service.IProductService;
import com.rdz.pitufos.domain.entity.ProductEntity;
import com.rdz.pitufos.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;

    @Override
    public List<ProductEntity> findAll() {
        return iProductRepository.findAllByOrderByName();
    }

    @Override
    public List<ProductEntity> findAllActive() {
        return iProductRepository.findByStatusTrueOrderByName();
    }

    @Override
    public boolean existsById(long id) {
        return iProductRepository.existsById(id);
    }

    @Override
    public Optional<ProductEntity> findById(long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return iProductRepository.save(productEntity);
    }

    @Override
    public void delete(Long id) {
        iProductRepository.deleteById(id);
    }
}
