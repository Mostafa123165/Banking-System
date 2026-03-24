package com.eazyBank.Card.mapper;

import com.eazyBank.Card.dto.CardsDto;
import com.eazyBank.Card.entity.Cards;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CardsMapper {

    CardsDto map(Cards cards);

    Cards unMap(CardsDto cardsDto);

}
