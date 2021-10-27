package com.pirates.repository;

import com.pirates.entity.Delivery;
import com.pirates.entity.Option;
import com.pirates.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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
        List<Option> options1 = new ArrayList<>();
        List<Option> options2 = new ArrayList<>();
        List<Option> options3 = new ArrayList<>();
        options1.add(Option.builder().price(1000).build());
        options1.add(Option.builder().price(1001).build());
        options2.add(Option.builder().price(1002).build());
        options2.add(Option.builder().price(1003).build());
        options3.add(Option.builder().price(1004).build());
        options3.add(Option.builder().price(1005).build());

        Product product1 = createProductWithOptions(options1);
        Product product2 = createProductWithOptions(options2);
        Product product3 = createProductWithOptions(options3);

        //when
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Long> productIds = new ArrayList<>();
        productIds.add(product1.getId());
        productIds.add(product2.getId());
        productIds.add(product3.getId());

        //then
        List<Long> lowestPrices = optionRepository.getLowestPrice(productIds);
        Assertions.assertEquals(1000, lowestPrices.get(0));
        Assertions.assertEquals(1002, lowestPrices.get(1));
        Assertions.assertEquals(1004, lowestPrices.get(2));
    }

    private Product createProductWithOptions(List<Option> options){
        return Product.builder()
                .name("p").delivery(Delivery.builder().price(1000).build())
                .options(options).build();
    }

}