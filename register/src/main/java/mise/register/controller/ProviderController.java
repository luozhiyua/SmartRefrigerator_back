package mise.register.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class ProviderController {
    @GetMapping("/provider/{name}")
    public String provider(@PathVariable String name) {
        return "hello," + name;
    }
}
