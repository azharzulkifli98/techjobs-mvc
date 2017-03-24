package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        ArrayList<HashMap<String, String>> data = null;
        String[] types = new String[]{"core competency", "employer", "location", "position type"};

        if (searchTerm.equals("")) {
            data = JobData.findAll();
        } else if (searchType.equals("all")) {
            data = JobData.findByValue(searchTerm);
        } else {
            data = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("tables", data);
        String amount = Integer.toString(data.size()) + " Result(s)";
        model.addAttribute("amount", amount);



        return "search";
    }

}
