package com.pirates.service;

import com.pirates.dto.ReceiveDateDto;
import com.pirates.dto.product.ProductGetDetailDto;
import com.pirates.dto.product.ProductGetDto;
import com.pirates.dto.product.ProductRegisterDto;
import com.pirates.entity.Product;
import com.pirates.repository.OptionRepository;
import com.pirates.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    @Transactional
    @Override
    public void register(ProductRegisterDto dto) {
        productRepository.save(dto.toEntity());
    }

    @Override
    public ProductGetDetailDto findOne(Long id) {
        return ProductGetDetailDto.toDto(checkIfNull(id));
    }

    @Override
    public List<ProductGetDto> findAll() {
        return toPersonGetDtoList(productRepository.findAll(
                Sort.by(Sort.Direction.DESC, "createDate")));
    }

    @Override
    public List<ReceiveDateDto> getReceiveDates(Long id) {
        return ReceiveDateDto.toDtoList(checkIfNull(id).getDelivery(), LocalDateTime.now());
    }

    @Transactional
    @Override
    public void remove(Long id) {
        productRepository.delete(checkIfNull(id));
    }

    private Product checkIfNull(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("상품이 존재하지 않습니다. id : " + id));
    }

    private List<ProductGetDto> toPersonGetDtoList(List<Product> products) {
        List<Long> idList = new ArrayList<>();
        products.forEach(p -> idList.add(p.getId()));
        List<Long> lowestPrices = optionRepository.getLowestPrice(idList);

        List<ProductGetDto> dtos = new ArrayList<>();
        IntStream.range(0, products.size())
                .forEach(i -> dtos.add(ProductGetDto.toDto(products.get(i), lowestPrices.get(i))));

        return dtos;
    }
}
