package net.yorksolutions.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServices {
    private final QuizRepository repository;

    @Autowired
    public QuizServices(QuizRepository repository) {
        this.repository = repository;
    }

    public Quiz createQuiz(Long quizTempId, int questionNumber, String question, String questionType) {
        Quiz newQuizCreated = new Quiz(quizTempId, questionNumber, question, questionType);
        repository.save(newQuizCreated);
        return newQuizCreated;
    }
}
