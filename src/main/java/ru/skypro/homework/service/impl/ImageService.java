package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.component.ImageProcessor;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class ImageService {

    @Value("${image.dir.path}")
    private String imageDir;

    private final UserService userService;
    private final ImageRepository imageRepository;

    public ImageService(UserService userService, ImageRepository imageRepository) {
        this.userService = userService;
        this.imageRepository = imageRepository;
    }

    public boolean updateUserAvatar(MultipartFile image, String login) throws IOException {
        Optional<User> userOptional = userService.getUserByLogin(login);
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        if (image == null ||image.getOriginalFilename()==null) {
            return false;
        }

        String fileName = image.getOriginalFilename();

        Path filepath = Path.of(imageDir, login.hashCode()
                + "." +ImageProcessor.getExtension(fileName));

        Image newImage = processImage(image, filepath);

        user.setImage(imageRepository.save(newImage));
        userService.updateUserImage(user);
        return true;
    }

    public Image addAdImage(MultipartFile image, long adId) throws IOException {

        if (image == null ||image.getOriginalFilename()==null) {
            throw new InputMismatchException();
        }

        String fileName = image.getOriginalFilename();

        Path filepath = Path.of(imageDir, "ad_"+adId
                + "." +ImageProcessor.getExtension(fileName));

        Image newImage = processImage(image, filepath);
        return imageRepository.save(newImage);
    }

    private Image processImage(MultipartFile image, Path filepath) throws IOException {
        Files.createDirectories(filepath.getParent());
        Files.deleteIfExists(filepath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filepath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Image newImage;
        if(imageRepository.existsByFilePath(filepath.toString())) {
            newImage = imageRepository.findByFilePath(filepath.toString());
        }
        else {
            newImage = new Image();
        }

        newImage.setFilePath(filepath.toString());
        newImage.setFileSize(image.getSize());
        newImage.setMediaType(image.getContentType());
        newImage.setPreview(ImageProcessor.generateImagePreview(filepath));
        return newImage;
    }
}
