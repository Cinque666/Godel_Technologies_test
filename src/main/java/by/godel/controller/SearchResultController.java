package by.godel.controller;

import by.godel.entities.Director;
import by.godel.entities.Film;
import by.godel.service.SearchResultService;
import by.godel.validation.DateValidator;
import by.godel.validation.DirectorIdValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static by.godel.controller.util.ControllerConstant.*;

@Controller
@RequestMapping("/searchResult")
public class SearchResultController {

    private static final Logger LOGGER = LogManager.getLogger(SearchResultController.class);

    @Autowired
    private SearchResultService searchResultService;

    @Autowired
    private DirectorIdValidator directorIdValidator;

    @Autowired
    private DateValidator dateValidator;

    @RequestMapping(method = RequestMethod.POST)
    public String getSearchResult(@ModelAttribute(value = MODEL_ATTRIBUTE_VALUE_DIRECTOR_ID) String id,
                                  BindingResult directorsBindingResult,
                                  @ModelAttribute(value = MODEL_ATTRIBUTE_VALUE_DISCOVER_DATE) String date,
                                  BindingResult dateBindingResult,
                                  Model model
                                  ) {

        directorIdValidator.validate(id, directorsBindingResult);
        dateValidator.validate(date, dateBindingResult);

        if(directorsBindingResult.hasErrors() && dateBindingResult.hasErrors()){
            model.addAttribute(MODEL_ATTRIBUTE_ERROR_MESSAGE, ERROR_MESSAGE);
            LOGGER.info("Validation failed. Client error message.");

            return SEARCH_FORM;
        }

        if(!directorsBindingResult.hasErrors() && !dateBindingResult.hasErrors()){
            //Оба параметра валидны. Вывести для конкретного режиссера фильмы начиная с конкретной даты.
            List<Film> films = searchResultService.getFilmByIdAndByDate(Long.valueOf(id), date);
            Director director = searchResultService.getDirectorById(Long.valueOf(id));

            if(films.size() == 0){
                model.addAttribute(MODEL_ATTRIBUTE_DIRECTOR, director);
                model.addAttribute(MODEL_ATTRIBUTE_PAGE_VERSION, "Director without films");

                return "searchResultNoFilms";

            } else{

                model.addAttribute(MODEL_ATTRIBUTE_SORTED_FILMS, films);
                model.addAttribute(MODEL_ATTRIBUTE_DIRECTOR, director);
                model.addAttribute(MODEL_ATTRIBUTE_PAGE_VERSION, MODEL_ATTRIBUTE_ONE_DIRECTOR);

                return SEARCH_RESULT;
            }
        }

        if(!directorsBindingResult.hasErrors() && dateBindingResult.hasErrors()){
            //В ид Директора нет ошибки, дата пустая. Вывести все фильмы для директора.
            Director director = searchResultService.getDirectorById(Long.valueOf(id));
            List<Film> films = searchResultService.getFilmsByDirectorId(director.getId());
            if(films.size() > 0) {
                model.addAttribute(MODEL_ATTRIBUTE_DIRECTOR, director);
                model.addAttribute("sortedFilms", films);
                model.addAttribute(MODEL_ATTRIBUTE_PAGE_VERSION, MODEL_ATTRIBUTE_ONE_DIRECTOR);

                return SEARCH_RESULT;
            } else {
                model.addAttribute(MODEL_ATTRIBUTE_DIRECTOR, director);
                model.addAttribute(MODEL_ATTRIBUTE_PAGE_VERSION, "Director without films");

                return "searchResultNoFilms";
            }
        }

        if(directorsBindingResult.hasErrors() && !dateBindingResult.hasErrors()){
            //Ид Директора неправильный, корректна только дата. Выводит все фильмы с даты с их режиссерами
            List<Director> directors = searchResultService.getAllDirectors();
            List<Film> films = searchResultService.getFilmsByDate(date);
                model.addAttribute(MODEL_ATTRIBUTE_DIRECTOR, directors);
                model.addAttribute(MODEL_ATTRIBUTE_SORTED_FILMS, films);
                model.addAttribute(MODEL_ATTRIBUTE_PAGE_VERSION, MODEL_ATTRIBUTE_FROM_DATE);
        }

        return SEARCH_RESULT;
    }
}
