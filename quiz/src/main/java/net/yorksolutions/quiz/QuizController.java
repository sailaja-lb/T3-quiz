package net.yorksolutions.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuizController {
    final private QuizServices service;

    @Autowired
    public QuizController(@NonNull QuizServices service){
        this.service = service;
    }

    @GetMapping("/createQuiz")
    @CrossOrigin
    public Quiz createQuiz(@RequestParam Long quizTempId, @RequestParam int questionNumber, @RequestParam String question, @RequestParam String questionType){
        return service.createQuiz(quizTempId, questionNumber, question, questionType);
    }

    @PostMapping("/createQuizA")
    @CrossOrigin
    public Quiz createQuizA(@RequestBody Quiz quiz){
        return service.createQuizA(quiz);
    }

    @GetMapping("/editQuiz")
    @CrossOrigin
    public void editQuiz(@RequestParam Long quizId, @RequestParam String question, @RequestParam String questionType){
        service.editQuiz(quizId, question, questionType);
    }

    @GetMapping("/deleteQuestion")
    @CrossOrigin
    public void deleteQuestion(@RequestParam Long quizId){
        service.deleteQuestion(quizId);
    }

    @GetMapping("/deleteQuiz")
    @CrossOrigin
    public void deleteQuiz(@RequestParam Long quizTempId){
        service.deleteQuiz(quizTempId);
    }

    @GetMapping("/getAllQuizzes")
    @CrossOrigin
    public Iterable<Quiz> getAllQuizzes(){
        return service.getAllQuizzes();
    }

}
