package com.rdz.pitufos.presentation.controller;


import com.rdz.pitufos.business.facade.IProductFacade;
import com.rdz.pitufos.domain.dto.request.ProductRequestDto;
import com.rdz.pitufos.domain.dto.response.ProductResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/product")
public class ProductController {

    private final IProductFacade iProductFacade;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iProductFacade.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ProductResponseDto>> findAllActive(){
        return ResponseEntity.status(HttpStatus.OK).body(iProductFacade.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iProductFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@ModelAttribute ProductRequestDto productRequestDto
                                                   ,@RequestPart MultipartFile image){
        return ResponseEntity.status(HttpStatus.CREATED).body(iProductFacade.save(productRequestDto,image));
    }


    @GetMapping(value = "/image/{filename}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getProductImage(@PathVariable("filename") String filename) throws IOException {
        Resource image = iProductFacade.getImage(filename);
        String contentType = Files.probeContentType(image.getFile().toPath());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .body(iProductFacade.getImage(filename));
    }

    @PatchMapping()
    public ResponseEntity<ProductResponseDto> update(@Valid @ModelAttribute ProductRequestDto productRequestDto,
                                                     @RequestParam(value = "image",required = false) MultipartFile file){
        return ResponseEntity.status(HttpStatus.OK).body(iProductFacade.update(productRequestDto,file));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id){
        return new ResponseEntity<>(iProductFacade.delete(id) ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }


}
