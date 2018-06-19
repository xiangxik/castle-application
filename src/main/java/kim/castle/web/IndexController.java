package kim.castle.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        return "/index";
    }
}
