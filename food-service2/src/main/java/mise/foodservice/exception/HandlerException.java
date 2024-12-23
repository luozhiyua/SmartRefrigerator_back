package mise.foodservice.exception;

import mise.foodservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {
//    @ExceptionHandler(MenuNotFoundException.class)
//    public ResponseEntity<Response> menuNotFoundException(MenuNotFoundException e){
//        return ResponseEntity.status(404).body(new Response(e.getMessage(),404, e.isError()));
//    }

    public ResponseEntity<Response> foodNotFoundException(FoodNotFoundException e){
        return ResponseEntity.status(404).body(new Response(e.getMessage(),404, e.isError()));
    }


    
}
