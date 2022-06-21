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

    public void createQuiz(Quiz quiz) {
        repository.save(quiz);
    }

    public void editQuiz(Long questionId, String questionText, String questionType) {
        Optional<Quiz> findQuiz = repository.findById(questionId);
        if (findQuiz.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            Quiz currentQuiz = findQuiz.get();
            currentQuiz.setQuestionText(questionText);
            currentQuiz.setQuestionType(questionType);
            repository.save(currentQuiz);
        }
    }

    public void deleteQuestion(Long questionId) {
        repository.deleteById(questionId);
    }

    public void deleteQuiz(Long quizTempId) {

        repository.deleteAllByQuizTemplateId(quizTempId);

    }

    public Iterable<Quiz> getAllQuizzes() {
        return repository.findAllByOrderByQuizTemplateIdAscQuestionNumberAsc();
    }

}
