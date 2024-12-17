package br.com.projeto.dreamshops.model.services.product;

import br.com.projeto.dreamshops.model.entities.Product;
import br.com.projeto.dreamshops.web.requests.AddProductRequest;
import br.com.projeto.dreamshops.web.requests.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);

}
