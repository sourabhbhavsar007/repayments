package exception;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ApiException extends RuntimeException {

   String errorMessage;
   HttpStatus exceptionCode;

   public ApiException(String message, HttpStatus exceptionCode)
   {
      super(message);
      this.errorMessage = message;
      this.exceptionCode = exceptionCode;
   }
}
