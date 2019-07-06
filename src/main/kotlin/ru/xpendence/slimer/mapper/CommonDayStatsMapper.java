package ru.xpendence.slimer.mapper;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.annotations.Mapper;
import ru.xpendence.slimer.dto.CommonDayStatsDto;
import ru.xpendence.slimer.entity.CommonDayStats;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 05.07.19
 * Time: 8:34
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = CommonDayStats.class, dto = CommonDayStatsDto.class)
public class CommonDayStatsMapper extends AbstractMapper<CommonDayStats, CommonDayStatsDto> {

    private final UserRepository userRepository;

    public CommonDayStatsMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(CommonDayStats.class, CommonDayStatsDto.class)
                .addMappings(m -> m.skip(CommonDayStatsDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommonDayStatsDto.class, CommonDayStats.class)
                .addMappings(m -> m.skip(CommonDayStats::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(CommonDayStats source, CommonDayStatsDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(CommonDayStatsDto source, CommonDayStats destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}
