package com.co.indra.coinmarketcap.coins.exceptions;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;

public class NotFoundException extends RuntimeException {
   public NotFoundException(ErrorCodes message) {
      super(message.getMessage());
   }
}