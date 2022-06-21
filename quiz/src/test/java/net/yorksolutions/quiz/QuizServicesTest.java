package net.yorksolutions.quiz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServicesTest {
    @InjectMocks
    QuizServices service;

    @Mock
    QuizRepository repository;

    @Test
    void itShouldAddAQuizWhenAddQuiz(){
        Long quizTempId = 0L;
        int questionNumber = 1;
        String question = "some question";
        String questionType = "text";
        Quiz expectedQuiz = new Quiz(quizTempId, questionNumber, question, questionType);
        when(repository.save(expectedQuiz)).thenReturn(new Quiz());
        //assertEquals(expectedQuiz, service.createQuiz(quizTempId, questionNumber, question, questionType));
    }

    @Test
    void itShouldUpdateQuizWhenEditQuizIsCalled(){
        Long quizTempId = 0L;
        int questionNumber = 1;
        String question = "update question";
        String questionType = "update text";
        Quiz expectedEditQuiz = new Quiz(quizTempId, questionNumber, "old question", "old text");
        Long id = expectedEditQuiz.getQuestionId();
        when(repository.findById(id)).thenReturn(Optional.of(expectedEditQuiz));
        ArgumentCaptor<Quiz> captor = ArgumentCaptor.forClass(Quiz.class);
        when(repository.save(captor.capture())).thenReturn(expectedEditQuiz);
        service.editQuiz(id, question, questionType);
        assertEquals(expectedEditQuiz, captor.getValue());
    }

    @Test
    void itShouldThrowNotFoundWhenIDIsNotInTheDatabase(){
        Long quizTempId = 0L;
        int questionNumber = 1;
        String question = "update question";
        String questionType = "update text";
        Quiz expectedEditQuiz = new Quiz(quizTempId, questionNumber, "old question", "old text");
        Long id = expectedEditQuiz.getQuestionId();
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.editQuiz(id, question, questionType));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }




}