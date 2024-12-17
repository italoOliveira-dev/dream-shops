package br.com.projeto.dreamshops.web.requests;

import br.com.projeto.dreamshops.model.entities.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

    private Category category;
}
