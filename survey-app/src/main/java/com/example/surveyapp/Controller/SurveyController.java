package com.example.surveyapp.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.surveyapp.Model.SurveyResponse;
import com.example.surveyapp.Repository.SurveyResponseRepository;

@Controller
public class SurveyController {

    @Autowired
    private SurveyResponseRepository repo;

    // Show the form
    @GetMapping("/")
    public String showSurveyForm(Model model) {
        model.addAttribute("survey", new SurveyResponse());
        return "survey";
    }

    // Handle form submission
    @PostMapping("/submit")
    public String submitSurvey(@ModelAttribute SurveyResponse survey,
                               @RequestParam(value = "favoriteFoods", required = false) List<String> favoriteFoods) {

        if (favoriteFoods != null) {
            survey.setFavoriteFoods(String.join(", ", favoriteFoods));
        } else {
            survey.setFavoriteFoods("");
        }

        repo.save(survey);
        return "redirect:/survey";
    }

    // Show survey results
    @GetMapping("/results")
public String viewResults(Model model) {
    List<SurveyResponse> all = repo.findAll();
    int total = all.size();
    model.addAttribute("count", total);

    if (total > 0) {
        int sumAges = 0;
        int validAgeCount = 0;

        Integer minAge = null;
        Integer maxAge = null;

        int movieTotal = 0;
        int radioTotal = 0;
        int eatOutTotal = 0;
        int tvTotal = 0;

        int pizzaCount = 0;
        int pastaCount = 0;
        int papCount = 0;

        for (SurveyResponse r : all) {
            int age = calculateAge(r.getDob());

            if (age > 0) {
                sumAges += age;
                validAgeCount++;

                if (minAge == null || age < minAge) minAge = age;
                if (maxAge == null || age > maxAge) maxAge = age;
            }

            movieTotal += r.getRatingWatchMovies();
            radioTotal += r.getRatingListenRadio();
            eatOutTotal += r.getRatingEatOut();
            tvTotal += r.getRatingWatchTV();

            String foods = r.getFavoriteFoods().toLowerCase();
            if (foods.contains("pizza")) pizzaCount++;
            if (foods.contains("pasta")) pastaCount++;
            if (foods.contains("pap and wors")) papCount++;
        }

        model.addAttribute("avgAge", validAgeCount > 0 ? sumAges / validAgeCount : 0);
        model.addAttribute("maxAge", maxAge != null ? maxAge : "-");
        model.addAttribute("minAge", minAge != null ? minAge : "-");

        model.addAttribute("pizzaPercent", String.format("%.1f", (double) pizzaCount * 100 / total));
        model.addAttribute("pastaPercent", String.format("%.1f", (double) pastaCount * 100 / total));
        model.addAttribute("papAndWorsPercent", String.format("%.1f", (double) papCount * 100 / total));

        model.addAttribute("avgMovies", String.format("%.1f", (double) movieTotal / total));
        model.addAttribute("avgRadio", String.format("%.1f", (double) radioTotal / total));
        model.addAttribute("avgEatOut", String.format("%.1f", (double) eatOutTotal / total));
        model.addAttribute("avgTV", String.format("%.1f", (double) tvTotal / total));
    }

    return "results";
}


// Helper method
private int calculateAge(String dobString) {
    if (dobString == null || dobString.isEmpty()) return 0;

    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(dobString, formatter);
        if (dob.isAfter(LocalDate.now())) return 0;
        return Period.between(dob, LocalDate.now()).getYears();
    } catch (Exception e) {
        return 0;
    }
}

}
