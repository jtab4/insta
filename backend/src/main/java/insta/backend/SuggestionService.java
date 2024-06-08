package insta.backend.service;

import insta.backend.model.User;
import insta.backend.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<User> getLastFiveUsersExcludingEmail(String excludeEmail) {
        return suggestionRepository.findFirst5ByEmailNotOrderByIdDesc(excludeEmail);
    }
}