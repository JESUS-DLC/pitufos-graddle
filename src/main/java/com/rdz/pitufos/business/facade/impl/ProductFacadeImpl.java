package com.rdz.pitufos.business.facade.impl;


import com.rdz.pitufos.business.exception.ResourceNotFoundExcpetion;
import com.rdz.pitufos.business.facade.IProductFacade;
import com.rdz.pitufos.business.mapper.IProductMapper;
import com.rdz.pitufos.business.service.IProductService;
import com.rdz.pitufos.domain.dto.request.ProductRequestDto;
import com.rdz.pitufos.domain.dto.response.ProductResponseDto;
import com.rdz.pitufos.domain.entity.ProductEntity;
import com.rdz.pitufos.domain.entity.UnitEntity;
import com.rdz.pitufos.repository.IUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductFacadeImpl implements IProductFacade {

    private final IUnitRepository iUnitRepository;
    private final IProductService iProductService;
    private final IProductMapper iProductMapper;
    private final ImageStorageService imageStorageService;
    //public static final String USER_FOLDER = System.getProperty("user.home") + "/pitufos/";

    @Override
    public List<ProductResponseDto> findAll() {
        //String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return iProductService.findAll().stream().map(iProductMapper::toProductResponseDto).toList();
    }

    @Override
    public List<ProductResponseDto> findAllActive() {
        return iProductService.findAllActive().stream().map(iProductMapper::toProductResponseDto).toList();
    }

    @Override
    public ProductResponseDto findById(long id) {
        ProductEntity productEntity = iProductService.findById(id).orElseThrow(ResourceNotFoundExcpetion::new);
        return iProductMapper.toProductResponseDto(productEntity);
    }

    @Override
    public ProductResponseDto save(ProductRequestDto productRequestDto, MultipartFile file) {
        UnitEntity unitEntity = iUnitRepository.findById(productRequestDto.getUnit()).orElseThrow(ResourceNotFoundExcpetion::new);
        ProductEntity productEntity = iProductMapper.toProductEntity(productRequestDto);
        String nameImage = imageStorageService.store(file);
        productEntity.setImage(nameImage);
        productEntity.setUnit(unitEntity);
        return iProductMapper.toProductResponseDto(iProductService.save(productEntity));
    }

    public ProductResponseDto update(ProductRequestDto productRequestDto,MultipartFile file){
        if(!iProductService.existsById(productRequestDto.getId())) throw new ResourceNotFoundExcpetion();
        UnitEntity unitEntity = iUnitRepository.findById(productRequestDto.getUnit()).orElseThrow(ResourceNotFoundExcpetion::new);
        ProductEntity productEntity = iProductMapper.toProductEntity(productRequestDto);
        ProductEntity productDb = iProductService.findById(productRequestDto.getId()).orElseThrow(ResourceNotFoundExcpetion::new);
        productEntity.setUnit(unitEntity);
        if(file != null){
            String imageName = imageStorageService.store(file);
            productEntity.setImage(imageName);
            imageStorageService.deleteFile(productDb.getImage());
        }else {
            productEntity.setImage(productDb.getImage());
        }
        return iProductMapper.toProductResponseDto(iProductService.save(productEntity));
    }

    @Override
    public boolean delete(Long id) {
        ProductEntity productEntity = iProductService.findById(id).orElseThrow(ResourceNotFoundExcpetion::new);
        imageStorageService.deleteFile(productEntity.getImage());
        iProductService.delete(id);
        return true;
    }

    @Override
    public Resource getImage(String filename) {
        return imageStorageService.getFile(filename);
    }

}
