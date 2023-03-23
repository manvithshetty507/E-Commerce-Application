package com.Ecommerce.AmazOff.DTO;

import com.Ecommerce.AmazOff.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardDTO {
    private String cardNo;
    private CardType cardType;
}
