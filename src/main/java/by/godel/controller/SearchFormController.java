package by.godel.controller;

import by.godel.controller.util.ControllerConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("searchForm")
public class SearchFormController {

    @RequestMapping(method = RequestMethod.GET)
    public String getSearchForm(Model model){
        return ControllerConstant.SEARCH_FORM;
    }
}
