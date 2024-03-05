package com.rdz.pitufos.business.facade;


import com.rdz.pitufos.domain.dto.request.ProductRequestDto;
import com.rdz.pitufos.domain.dto.response.ProductResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductFacade {
    List<ProductResponseDto> findAll();
    List<ProductResponseDto> findAllActive();
    ProductResponseDto findById(long id);
    ProductResponseDto save(ProductRequestDto productPojo, MultipartFile image);
    ProductResponseDto update(ProductRequestDto productPojo,MultipartFile file);
    boolean delete(Long id);
    Resource getImage(String filename);

}
