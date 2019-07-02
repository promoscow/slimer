package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.PortionDto;
import ru.xpendence.slimer.entity.Portion;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.repository.MealRepository;
import ru.xpendence.slimer.repository.ProductRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:18
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Portion.class, dto = PortionDto.class)
public class PortionMapper extends AbstractMapper<Portion, PortionDto> {

    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    public PortionMapper(MealRepository mealRepository,
                         ProductRepository productRepository) {
        this.mealRepository = mealRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Portion.class, PortionDto.class)
                .addMappings(m -> m.skip(PortionDto::setMeal)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(PortionDto.class, Portion.class)
                .addMappings(m -> m.skip(Portion::setMeal)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Portion source, PortionDto destination) {
        whenNotNull(source.getMeal(), m -> destination.setMeal(m.getId()));
    }

    @Override
    protected void mapSpecificFields(PortionDto source, Portion destination) {
        whenNotNull(source.getMeal(), m -> destination.setMeal(mealRepository.findById(m).orElse(null)));
    }
}
