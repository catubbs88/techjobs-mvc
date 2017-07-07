package org.launchcode.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String searchResults(HttpServletRequest request, Model model) {
        ArrayList<HashMap<String,String>> jobs = new ArrayList<HashMap<String, String>>();

        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");

        if (searchType.equals("all")) {
            jobs = (JobData.findByValue(searchTerm));
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        } else {
            jobs = (JobData.findByColumnAndValue(searchType, searchTerm));
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        }
    }
}
