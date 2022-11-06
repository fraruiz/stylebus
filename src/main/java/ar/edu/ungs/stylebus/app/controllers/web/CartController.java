package ar.edu.ungs.stylebus.app.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class CartController {
    @RequestMapping("/cart")
    public String index() {
        return "cart";
    }
}

