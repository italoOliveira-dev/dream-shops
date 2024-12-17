package br.com.projeto.dreamshops.model.services.image;

import br.com.projeto.dreamshops.model.entities.Image;
import br.com.projeto.dreamshops.web.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImageById(MultipartFile file, Long imageId);
}
