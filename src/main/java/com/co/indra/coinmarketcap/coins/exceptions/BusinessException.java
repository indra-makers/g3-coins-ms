package com.co.indra.coinmarketcap.coins.exceptions;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;

public class BusinessException extends RuntimeException {

   private ErrorCodes code;

   // Constructor de clase superior
   public BusinessException(String message) {
      super(message);
   }

   // Constructor que recibe el mensaje y el codigo por parametros

   public BusinessException(ErrorCodes code) {
      super(code.getMessage());
      this.code = code;
   }

   public String getCode() {
      return code.getCode();
   }

}