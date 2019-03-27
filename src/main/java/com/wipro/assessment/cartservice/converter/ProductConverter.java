package com.wipro.assessment.cartservice.converter;

import com.wipro.assessment.cartservice.entity.Product;
import com.wipro.assessment.cartservice.resource.ProductResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductConverter {

    public Product resourceToEntity(ProductResource resource) {
        return Product.builder()
                .name( resource.getName() )
                .description( resource.getDescription() )
                .build();
    }

    public ProductResource entityToResource(Product product) {
        return ProductResource.builder()
                .id( product.getId() )
                .name( product.getName() )
                .description( product.getDescription() )
                .cost( product.getCost() )
                .build();
    }

}
