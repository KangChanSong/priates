package com.pirates.service;

import com.pirates.dto.product.ProductGetDetailDto;
import com.pirates.dto.product.ProductGetDto;
import com.pirates.dto.product.ProductRegisterDto;
import com.pirates.dto.ReceiveDateDto;

public interface ProductService {
    void add(ProductRegisterDto dto);
    ProductGetDetailDto findOne(Long id);
    ProductGetDto findAll();
    ReceiveDateDto getReceiveDates(Long id);
    void remove(Long id);
}
