package net.yorksolutions.quiz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(expectedQuiz, service.createQuiz(quizTempId, questionNumber, question, questionType));
    }
}