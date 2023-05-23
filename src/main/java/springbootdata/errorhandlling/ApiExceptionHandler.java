package springbootdata.errorhandlling;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springbootdata.model.ErrorDetails;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleApiException(APIException apiException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(apiException.getStatus().getReasonPhrase());
        errorDetails.setMessage(apiException.getMessage());
        return new ResponseEntity<>(errorDetails, apiException.getStatus());
    }


}
