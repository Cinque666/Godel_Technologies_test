package by.godel.controller;

import by.godel.entities.Director;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = by.godel.config.WebAppConfiguration.class)
@WebAppConfiguration
public class SearchResultControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstSearch() throws Exception {
        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(post("/searchResult").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(buildUrlEncodedFormEntity(
                        "directorId", "2",
                        "discoverDate", "1960-01-21")))
                        .andReturn()
                        .getModelAndView();
        viewName = modelAndView.getViewName();
        List items = (List)modelAndView.getModel().get("sortedFilms");

        assertEquals("searchResult", viewName);
        assertNotNull(items);
        assertEquals(4, items.size());
    }

    @Test
    public void secondSearch() throws Exception {
        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(post("/searchResult").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(buildUrlEncodedFormEntity(
                                "directorId", "1",
                                "discoverDate", "")))
                        .andReturn()
                        .getModelAndView();
        viewName = modelAndView.getViewName();
        Director item = (Director)modelAndView.getModel().get("directors");

        assertEquals("searchResultNoFilms", viewName);
        assertNotNull(item);
    }

    @Test
    public void thirdSearch() throws Exception {
        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(post("/searchResult").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(buildUrlEncodedFormEntity(
                                "directorId", "",
                                "discoverDate", "")))
                        .andReturn()
                        .getModelAndView();
        viewName = modelAndView.getViewName();

        assertEquals("searchForm", viewName);
    }

    private String buildUrlEncodedFormEntity(String... params) {
        if( (params.length % 2) > 0 ) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i=0; i<params.length; i+=2) {
            if( i > 0 ) {
                result.append('&');
            }
            try {
                result.
                        append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
                        append('=').
                        append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }
}