package com.rdz.pitufos.business.facade.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    public String store(MultipartFile file){
        try {
            if(file.isEmpty()){
                throw new RuntimeException("failed to store data");
            }
            String fileOriginalName = file.getOriginalFilename();
            if(!fileOriginalName.endsWith(".jpg") &&
                            !fileOriginalName.endsWith(".jpeg") &&
                            !fileOriginalName.endsWith(".png")
            ){
                throw new RuntimeException("is not an image");
            }
            String fileName = UUID.randomUUID().toString();
            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String finalName = fileName + fileExtension;

            Path destinationFile = rootLocation.resolve(Paths.get(finalName)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return finalName;
        }catch (Exception e){
            throw new RuntimeException("failed to store file",e);
        }
    }

    public Resource getFile(String filename){
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){

                return resource;
            }else {
                throw new RuntimeException("no se puede leer el archivo");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("could not read file");
        }
    }

    public void deleteFile(String filename){
        try{
        Path path = rootLocation.resolve(filename);
        if(Files.exists(path)){
            Files.delete(path);
        }
        }catch (Exception e){
            throw new RuntimeException("cant delete image");
        }
    }
}
