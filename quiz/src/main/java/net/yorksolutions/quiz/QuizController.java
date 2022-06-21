package net.yorksolutions.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuizController {
    final private QuizServices service;

    @Autowired
    public QuizController(@NonNull QuizServices service) {
        this.service = service;
    }

    @GetMapping("/createQuiz")
    @CrossOrigin
    public Iterable<Quiz> createQuiz(@RequestParam Long quizTempId, @RequestParam int questionNumber, @RequestParam String questionText, @RequestParam String questionType) {
        service.createQuiz(quizTempId, questionNumber, questionText, questionType);
        return service.getAllQuizzes();
    }

    @PostMapping("/createQuizA")
    @CrossOrigin
    public Iterable<Quiz> createQuizA(@RequestBody Quiz quiz) {
        service.createQuizA(quiz);
        return service.getAllQuizzes();
    }

    @GetMapping("/editQuiz")
    @CrossOrigin
    public Iterable<Quiz> editQuiz(@RequestParam Long questionId, @RequestParam String questionText, @RequestParam String questionType) {
        service.editQuiz(questionId, questionText, questionType);
        return service.getAllQuizzes();
    }

    @GetMapping("/deleteQuestion")
    @CrossOrigin
    public Iterable<Quiz> deleteQuestion(@RequestParam Long questionId) {
        service.deleteQuestion(questionId);
        return service.getAllQuizzes();
    }

    @DeleteMapping("/deleteQuiz/{quizTemplateId}")
    @CrossOrigin
    public Iterable<Quiz> deleteQuiz(@PathVariable("quizTemplateId") Long quizTempId) {
        service.deleteQuiz(quizTempId);
        return service.getAllQuizzes();
    }

    @GetMapping("/getAllQuizzes")
    @CrossOrigin
    public Iterable<Quiz> getAllQuizzes() {
        return service.getAllQuizzes();
    }

}
