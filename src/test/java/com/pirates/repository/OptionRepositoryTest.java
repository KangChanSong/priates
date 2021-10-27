package com.pirates.repository;

import com.pirates.entity.Delivery;
import com.pirates.entity.Option;
import com.pirates.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class OptionRepositoryTest {

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 옵션_등록_조회(){
        //given
        String name = "이름";
        int price = 12000;

        Option option = Option.builder().name(name).price(price).build();

        //when
        optionRepository.save(option);
        Option foundOption = optionRepository.findById(option.getId()).get();

        //then
        assertEquals(foundOption.getName(), option.getName());
        assertEquals(foundOption.getPrice(), option.getPrice());
        assertEquals(foundOption, option);
    }

    @DisplayName("상품 id 에 해당하는 옵션 가격 중 가장 낮은 가격을 조회한다.")
    @Test
    public void GET_LOWEST_PRICE(){
        //given
        List<Option> options = new ArrayList<>();
        IntStream.range(0, 100).forEach(i-> options.add(Option.builder().name("a").price(1000 + i).build()));

        Product product = Product.builder()
                .name("product")
                .delivery(Delivery.builder().price(1000).build())
                .options(options)
                .build();

        //when
        productRepository.save(product);

        //then
        Long lowestPrice = optionRepository.getLowestPrice(product.getId()).orElse(0L);
        assertEquals(1000, lowestPrice);
    }

}