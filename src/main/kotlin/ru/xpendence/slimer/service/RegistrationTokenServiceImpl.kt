package ru.xpendence.slimer.service

import org.springframework.stereotype.Service
import ru.xpendence.slimer.annotations.ServiceImpl
import ru.xpendence.slimer.dto.RegistrationTokenDto
import ru.xpendence.slimer.entity.RegistrationToken
import ru.xpendence.slimer.mapper.impl.RegistrationTokenMapper
import ru.xpendence.slimer.repository.RegistrationTokenRepository

/**
 * Author: Vyacheslav Chernyshov
 * Date: 19.07.19
 * Time: 21:36
 * e-mail: v.chernyshov@pflb.ru
 */
@Service
@ServiceImpl
class RegistrationTokenServiceImpl
    : AbstractService<RegistrationToken, RegistrationTokenDto, RegistrationTokenMapper, RegistrationTokenRepository>()