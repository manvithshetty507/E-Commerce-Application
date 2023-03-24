package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CardRequestDTO;
import com.Ecommerce.AmazOff.Model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConvertor {
    public static Card cardRequestDTOtoCard(CardRequestDTO cardRequestDTO){
        return Card.builder()
                .cardNo(cardRequestDTO.getCardNo())
                .cvv(cardRequestDTO.getCvv())
                .cardType(cardRequestDTO.getCardType())
                .build();
    }
}
