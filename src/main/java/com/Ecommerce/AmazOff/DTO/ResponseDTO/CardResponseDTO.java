package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import com.Ecommerce.AmazOff.DTO.CardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardResponseDTO {

    private String name;
    private List<CardDTO> cardDTOs;
}
