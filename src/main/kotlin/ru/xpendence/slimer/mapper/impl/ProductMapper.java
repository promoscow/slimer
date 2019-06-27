package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.ProductDto;
import ru.xpendence.slimer.entity.Product;
import ru.xpendence.slimer.mapper.AbstractMapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-27
 * Time: 20:33
 * e-mail: 2262288@gmail.com
 */
@Mapper(entity = Product.class, dto = ProductDto.class)
@Component
public class ProductMapper extends AbstractMapper<Product, ProductDto> {
}
