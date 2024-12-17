package br.com.projeto.dreamshops.web.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
