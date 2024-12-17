package br.com.projeto.dreamshops.model.services.image;

import br.com.projeto.dreamshops.exceptions.ResourceNotFoundException;
import br.com.projeto.dreamshops.model.entities.Image;
import br.com.projeto.dreamshops.model.entities.Product;
import br.com.projeto.dreamshops.model.repositories.ImageRepository;
import br.com.projeto.dreamshops.model.services.product.ProductService;
import br.com.projeto.dreamshops.web.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found!"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image not found!");
        });
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {

        Product product = productService.getProductById(productId);

        List<ImageDto> savedImageDto = new ArrayList<>();
        try {
            Image image = new Image();
            for (MultipartFile file : files) {
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl+image.getId();
                image.setDownloadUrl(downloadUrl);

                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());

                savedImageDto.add(imageDto);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return savedImageDto;
    }

    @Override
    public void updateImageById(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
