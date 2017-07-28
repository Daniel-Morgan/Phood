package com.doughepi.controllers;

import com.doughepi.models.SearchResults;
import com.doughepi.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pjdoughe on 3/23/17.
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(value = "/search", params = {"query"})
    public String showSearch(Model model, @RequestParam("query") String query) {



        SearchResults searchResults = searchService.getSearchResults(query);
        model.addAttribute("query", searchResults.getQuery());
        model.addAttribute("count", searchResults.getCount());
        model.addAttribute("duration", searchResults.getDuration());
        model.addAttribute("unit", searchResults.getUnit());

        model.addAttribute("results", searchResults.getResultList());

        return "search";
    }

}
