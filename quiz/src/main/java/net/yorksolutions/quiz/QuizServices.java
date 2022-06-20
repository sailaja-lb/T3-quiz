package net.yorksolutions.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    public void editQuiz(Long id, String question, String questionType){
        Optional<Quiz> findQuiz = repository.findById(id);
        if (findQuiz.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            Quiz currentQuiz = findQuiz.get();
            currentQuiz.setQuizQuestion(question);
            currentQuiz.setQuizQuestion(questionType);
            repository.save(currentQuiz);
        }
    }

}
