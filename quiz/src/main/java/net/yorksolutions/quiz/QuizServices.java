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

    public Quiz createQuiz(Long quizTempId, int questionNumber, String questionText, String questionType) {
        Quiz newQuizCreated = new Quiz(quizTempId, questionNumber, questionText, questionType);
        repository.save(newQuizCreated);
        return newQuizCreated;
    }
    public Quiz createQuizA(Quiz quiz){
        return repository.save(quiz);
    }

    public void editQuiz(Long quizId, String questionText, String questionType){
        Optional<Quiz> findQuiz = repository.findById(quizId);
        if (findQuiz.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            Quiz currentQuiz = findQuiz.get();
            currentQuiz.setQuestionText(questionText);
            currentQuiz.setQuestionType(questionType);
            repository.save(currentQuiz);
        }
    }

    public void deleteQuestion(Long quizId){
        repository.deleteById(quizId);
    }

    public void deleteQuiz(Long quizId){
        Optional<Quiz> existingQuiz = repository.findByQuizTemplateId(quizId);

        if (existingQuiz.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            Long quizTemplateId = existingQuiz.get().getQuizTemplateId();
            //Quiz quizTemplateId = existingQuiz.get();
            repository.deleteById(quizTemplateId);
        }
    }

    public Iterable<Quiz> getAllQuizzes(){
        return repository.findAll();
    }

}
