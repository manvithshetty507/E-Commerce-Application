package com.Ecommerce.AmazOff.Exception;

import lombok.experimental.UtilityClass;


public class InsufficientQuantity extends Exception{

    public InsufficientQuantity(String message){
        super(message);
    }

}
