package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.ProductDto
import ru.xpendence.slimer.entity.Product
import ru.xpendence.slimer.mapper.impl.ProductMapper
import ru.xpendence.slimer.repository.ProductRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-27
 * Time: 20:37
 * e-mail: 2262288@gmail.com
 */
@Service
@ServiceImpl
class ProductServiceImpl : AbstractService<Product, ProductDto, ProductMapper, ProductRepository>()