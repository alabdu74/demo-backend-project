package com.example.demo2.mapper;

import com.example.demo2.model.dto.ProductRequest;
import com.example.demo2.model.dto.ProductResponse;
import com.example.demo2.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product product);

    void updateEntity(ProductRequest request, @MappingTarget Product product);
}