package com.pirates.repository;

import com.pirates.entity.Delivery;
import com.pirates.entity.DeliveryType;
import com.pirates.entity.Option;
import com.pirates.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class ProductRepositoryTest {

    private static final String PRODUCT_NAME = "이름";
    private static final String PRODUCT_DESCRIPTION = "설명";

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OptionRepository optionRepository;

    @Test
    public void 상품_등록_조회(){
        //given
        Delivery delivery = createDelivery();

        Product product = Product.builder()
                .name(PRODUCT_NAME).description(PRODUCT_DESCRIPTION).delivery(delivery).build();
        //when
        productRepository.save(product);
        Product foundProduct = productRepository.findById(product.getId()).get();
        //then
        assertEquals(foundProduct.getName(),product.getName());
        assertEquals(foundProduct.getDescription(), product.getDescription());
        assertEquals(foundProduct.getDelivery().getType(), delivery.getType());
        assertEquals(product, foundProduct);
    }

    @Test
    public void 상품_옵션과_조회(){
        //given
        int optionSize = 10;
        String optionName = "옵션";

        List<Option> options = new ArrayList<>();
        IntStream.range(0, optionSize).forEach(i -> {
            options.add(Option.builder().name(optionName + i).build());
        });

        Product product = Product.builder()
                .name(PRODUCT_NAME)
                .delivery(createDelivery())
                .options(options).build();

        //when
        productRepository.save(product);
        Product foundProduct = productRepository.findById(product.getId()).get();

        //then
        assertEquals(foundProduct.getOptions().size(), optionSize);
        foundProduct.getOptions().forEach(option -> {
            assertTrue(option.getName().contains(optionName));
        });
    }


    private Delivery createDelivery(){
        return Delivery.builder().type(DeliveryType.fast).build();
    }

}