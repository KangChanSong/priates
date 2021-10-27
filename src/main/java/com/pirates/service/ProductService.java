package com.pirates.service;

import com.pirates.dto.ReceiveDateDto;
import com.pirates.dto.product.ProductGetDetailDto;
import com.pirates.dto.product.ProductGetDto;
import com.pirates.dto.product.ProductRegisterDto;

import java.util.List;

public interface ProductService {
    void register(ProductRegisterDto dto);
    ProductGetDetailDto findOne(Long id);
    List<ProductGetDto> findAll();
    List<ReceiveDateDto> getReceiveDates(Long id);
    void remove(Long id);
}
