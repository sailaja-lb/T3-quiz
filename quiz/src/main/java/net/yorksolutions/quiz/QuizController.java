package net.yorksolutions.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuizController {
    private QuizServices service;

    @Autowired
    public QuizController(@NonNull QuizServices service) {
        this.service = service;
    }

    @PostMapping("/createQuiz")
    @CrossOrigin
    public Iterable<Quiz> createQuiz(@RequestBody Quiz quiz) {
        service.createQuiz(quiz);
        return service.getAllQuizzes();
    }

    @GetMapping("/editQuiz")
    @CrossOrigin
    public Iterable<Quiz> editQuiz(@RequestParam Long questionId, @RequestParam String questionText, @RequestParam String questionType) {
        service.editQuiz(questionId, questionText, questionType);
        return service.getAllQuizzes();
    }

    @DeleteMapping("/deleteQuestion/{questionId}")
    @CrossOrigin
    public Iterable<Quiz> deleteQuestion(@PathVariable("questionId") Long questionId) {
        service.deleteQuestion(questionId);
        return service.getAllQuizzes();
    }

    @DeleteMapping("/deleteQuiz/{quizTemplateId}")
    @CrossOrigin
    public Iterable<Quiz> deleteQuiz(@PathVariable("quizTemplateId") Long quizTempId) {
        service.deleteQuiz(quizTempId);
        return service.getAllQuizzes();
    }

    @GetMapping("/getQuizToRespond")
    @CrossOrigin
    public Iterable<Quiz> getQuizToRespond(@RequestParam Long quizTempId){
        service.getQuizToRespond(quizTempId);
        return service.getAllQuizzes();
    }

    @GetMapping("/getAllQuizzes")
    @CrossOrigin
    public Iterable<Quiz> getAllQuizzes() {
        return service.getAllQuizzes();
    }

    public void setService(QuizServices service) {
        this.service = service;
    }
}
