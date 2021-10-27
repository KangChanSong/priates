package com.pirates.controller;

import com.pirates.dto.ReceiveDateDto;
import com.pirates.dto.product.ProductGetDetailDto;
import com.pirates.dto.product.ProductGetDto;
import com.pirates.dto.product.ProductRegisterDto;
import com.pirates.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/product/register")
    public void register(@RequestBody ProductRegisterDto dto){
        productService.register(dto);
    }

    @GetMapping("/api/v1/product/list")
    public List<ProductGetDto> getList(){
        return productService.findAll();
    }

    @GetMapping("/api/v1/product/get/{productId}")
    public ProductGetDetailDto get(@PathVariable("productId") Long id){
        return productService.findOne(id);
    }

    @GetMapping("/api/v1/product/receive-dates/{productId}")
    public List<ReceiveDateDto> getReceiveDates(@PathVariable("productId") Long id){
        return productService.getReceiveDates(id);
    }

    @DeleteMapping("/api/v1/product/delete/{productId}")
    public void delete(@PathVariable("productId") Long id){
        productService.remove(id);
    }

}
