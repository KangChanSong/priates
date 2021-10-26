package com.pirates.repository;

import com.pirates.entity.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OptionRepositoryTest {

    @Autowired
    OptionRepository repository;

    @Test
    public void 옵션_등록_조회(){
        //given
        String name = "이름";
        int price = 12000;

        Option option = Option.builder().name(name).price(price).build();

        //when
        repository.save(option);
        Option foundOption = repository.findById(option.getId()).get();

        //then
        assertEquals(foundOption.getName(), option.getName());
        assertEquals(foundOption.getPrice(), option.getPrice());
        assertEquals(foundOption, option);
    }
}