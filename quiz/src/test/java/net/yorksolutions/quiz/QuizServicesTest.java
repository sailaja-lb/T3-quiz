package net.yorksolutions.quiz;

import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServicesTest {
    @InjectMocks
    QuizServices service;

    @Mock
    QuizRepository repository;


    @Test
    void itShouldCreateQuizAndSaveInRepository(){
        Quiz expectedQuiz = new Quiz();
        service.createQuiz(expectedQuiz);
        verify(repository).save(expectedQuiz);
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
    @Test
    void itShouldRemoveAQuestionFromRepositoryWhenDeleteById(){
        final Long questionId = 0L;
        doNothing().when(repository).deleteById(questionId);
        service.deleteQuestion(questionId);
        verify(repository).deleteById(questionId);
    }

    @Test
    void itShouldRemoveAllQuizTempIdFromRepositoryWhenDeleteAllByQuizTemplateId(){
        Long quizTempId = 0L;
        int questionNumber = 1;
        String questionText = "some question";
        String questionType = "text";
        Quiz someQuiz = new Quiz(quizTempId, questionNumber, questionText, questionType);
        ArrayList<Quiz> expectedQuiz = new ArrayList<>();
        when(repository.deleteAllByQuizTemplateId(quizTempId)).thenReturn(expectedQuiz);
        service.deleteQuiz(quizTempId);
        verify(repository).deleteAllByQuizTemplateId(quizTempId);
    }

    @Test
    void itShouldFindAllQuizTempIdAndQuestionNumberInAscendingOrder(){
        Long quizTempId = 0L;
        int questionNumber = 1;
        String questionText = "some question";
        String questionType = "text";
        Quiz someQuiz = new Quiz(quizTempId, questionNumber, questionText, questionType);
        ArrayList<Quiz> expectedQuiz = new ArrayList<>();
        expectedQuiz.add(someQuiz);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        when(repository.findAllByOrderByQuizTemplateIdAscQuestionNumberAsc()).thenReturn(expectedQuiz);
        Iterable<Quiz> result = service.getAllQuizzes();
        assertEquals(expectedQuiz, result);
    }


}