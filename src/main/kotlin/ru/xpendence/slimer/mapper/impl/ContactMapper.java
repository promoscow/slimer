package ru.xpendence.slimer.mapper.impl;

import org.springframework.stereotype.Component;
import ru.xpendence.slimer.dto.ContactDto;
import ru.xpendence.slimer.entity.Contact;
import ru.xpendence.slimer.mapper.AbstractMapper;
import ru.xpendence.slimer.mapper.Mapper;
import ru.xpendence.slimer.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 2019-06-20
 * Time: 20:20
 * e-mail: 2262288@gmail.com
 */
@Component
@Mapper(entity = Contact.class, dto = ContactDto.class)
public class ContactMapper extends AbstractMapper<Contact, ContactDto> {

    private final UserRepository userRepository;

    public ContactMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(Contact.class, ContactDto.class)
                .addMappings(m -> m.skip(ContactDto::setUser)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ContactDto.class, Contact.class)
                .addMappings(m -> m.skip(Contact::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Contact source, ContactDto destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(u.getId()));
    }

    @Override
    protected void mapSpecificFields(ContactDto source, Contact destination) {
        whenNotNull(source.getUser(), u -> destination.setUser(userRepository.findById(u).orElse(null)));
    }
}
