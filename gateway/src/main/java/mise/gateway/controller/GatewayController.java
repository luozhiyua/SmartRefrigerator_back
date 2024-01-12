package mise.gateway.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/gateway")
@CrossOrigin(origins = "*")
public class GatewayController {

        @GetMapping("/test")
        public String test(){
            return "gateway open";
        }
}
