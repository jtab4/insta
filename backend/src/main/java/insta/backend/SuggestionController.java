package insta.backend.controller;

import insta.backend.model.User;
import insta.backend.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/lastFiveExcluding/{email}")
    public List<User> getLastFiveUsersExcludingEmail(@PathVariable String email) {
        return suggestionService.getLastFiveUsersExcludingEmail(email);
    }
}
