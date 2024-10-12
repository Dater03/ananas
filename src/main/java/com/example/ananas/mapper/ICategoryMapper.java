package com.example.ananas.mapper;

import com.example.ananas.dto.request.CategoryCreateRequest;
import com.example.ananas.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    CategoryCreateRequest toCategory (CategoryCreateRequest categoryCreateRequest);
}
