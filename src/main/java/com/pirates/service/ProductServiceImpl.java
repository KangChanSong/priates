package com.pirates.service;

import com.pirates.dto.ReceiveDatesDto;
import com.pirates.dto.product.ProductGetDetailDto;
import com.pirates.dto.product.ProductGetDto;
import com.pirates.dto.product.ProductRegisterDto;
import com.pirates.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public void add(ProductRegisterDto dto) {
        
    }

    @Override
    public ProductGetDetailDto findOne(Long id) {
        return null;
    }

    @Override
    public ProductGetDto findAll() {
        return null;
    }

    @Override
    public ReceiveDatesDto getReceiveDates(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
