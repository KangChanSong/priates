package com.pirates.service;

import com.pirates.repository.OptionRepository;
import com.pirates.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;
    @Mock
    OptionRepository optionRepository;

    @BeforeEach
    public void setUp(){
        this.productService = new ProductServiceImpl(productRepository, optionRepository);
    }

    @DisplayName("id 로 상품을 찾지 못했을때 예외를 던진다.")
    @Test
    public void THROW_IF_PRODUCT_NULL(){
        //given
        Long productId = 1L;
        //when
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        //then
        assertThrows(IllegalStateException.class, () -> productService.remove(productId));
        assertThrows(IllegalStateException.class, () -> productService.findOne(productId));
        assertThrows(IllegalStateException.class, () -> productService.getReceiveDates(productId));
    }
}