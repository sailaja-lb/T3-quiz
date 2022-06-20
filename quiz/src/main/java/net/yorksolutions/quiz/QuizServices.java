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
    public Quiz createQuizA(Quiz quiz){
        return repository.save(quiz);
    }

    public void editQuiz(Long quizId, String question, String questionType){
        Optional<Quiz> findQuiz = repository.findById(quizId);
        if (findQuiz.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            Quiz currentQuiz = findQuiz.get();
            currentQuiz.setQuizQuestion(question);
            currentQuiz.setQuizQuestion(questionType);
            repository.save(currentQuiz);
        }
    }

    public void deleteQuestion(Long quizId){
        repository.deleteById(quizId);
    }

    public void deleteQuiz(Long quizTempId){

        repository.deleteById(quizTempId);
    }

    public Iterable<Quiz> getAllQuizzes(){
        return repository.findAll();
    }

}
