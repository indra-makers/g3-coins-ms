package com.co.indra.coinmarketcap.coins.exceptions;

import com.co.indra.coinmarketcap.coins.config.ErrorCodes;
import com.co.indra.coinmarketcap.coins.model.responses.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

   @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
   @ResponseBody // que la respuesta va a ser personalizada.
   @ExceptionHandler(BusinessException.class)
   public ErrorResponse handleBusinessException(BusinessException exception) {
      return new ErrorResponse(exception.getCode(), exception.getMessage());
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   @ExceptionHandler(NotFoundException.class)
   public ErrorResponse handleNotFoundException(NotFoundException exception) {
      return new ErrorResponse("NOT_FOUND", exception.getMessage());
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handleNotFoundException(MethodArgumentNotValidException exception) {
      return new ErrorResponse("BAD_PARAMETERS", exception.getMessage());
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   @ExceptionHandler(HttpClientErrorException.NotFound.class)
   public ErrorResponse handleNotFoundExceptionExternalApi(HttpClientErrorException.NotFound exception) {
      return new ErrorResponse("ERROR", ErrorCodes.ERROR_COINCAP_API.getMessage());
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ResponseBody
   @ExceptionHandler(Exception.class)
   public ErrorResponse handleException(Exception exception) {
      exception.printStackTrace();
      return new ErrorResponse("500", exception.getMessage());
   }
}