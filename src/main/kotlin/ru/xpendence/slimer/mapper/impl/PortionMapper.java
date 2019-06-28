package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.PortionDto;
import ru.xpendence.slimer.entity.Portion;
import ru.xpendence.slimer.mapper.AbstractMapper;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 28.06.19
 * Time: 14:18
 * e-mail: v.chernyshov@pflb.ru
 */
@Component
@Mapper(entity = Portion.class, dto = PortionDto.class)
public class PortionMapper extends AbstractMapper<Portion, PortionDto> {
}
