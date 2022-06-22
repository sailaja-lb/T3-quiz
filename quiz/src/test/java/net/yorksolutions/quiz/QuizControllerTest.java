package net.yorksolutions.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizControllerTest {
    @LocalServerPort
    int port;

    @Autowired
    QuizController controller;

    @Mock
    QuizServices service;

    @BeforeEach
    void setUp(){
        controller.setService(service);
    }

//    @Test
//    void itShouldCallCreateQuizFromServiceWithQuizObjReturnGetAllQuizzes(){
//        Quiz someQuiz = new Quiz();
//        ArrayList<Quiz> expectedQuiz = new ArrayList<>();
//        TestRestTemplate rest = new TestRestTemplate();
//        final String url = "http://localhost:" + port + "/createQuiz";
//        lenient().doNothing().when(service).createQuiz(someQuiz);
//        when(service.getAllQuizzes()).thenReturn(expectedQuiz);
//        ResponseEntity<Void> response = rest.postForEntity(url,)
//        Iterable<Quiz> result = service.getAllQuizzes();
//        assertEquals(expectedQuiz, result);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    void itShouldCallEditQuizFromServiceAndReturnGetAllQuizzes(){
        Long questionId = 0L;
        String questionText = "some question";
        String questionType = "text";
        ArrayList<Quiz> expectedQuiz = new ArrayList<>();
        TestRestTemplate rest = new TestRestTemplate();
        final String url = "http://localhost:" + port + "/editQuiz?questionId=" + questionId
                + "&questionText=" + questionText + "&questionType=" + questionType;
        doThrow(new ResponseStatusException(HttpStatus.ACCEPTED))
                .when(service).editQuiz(questionId,questionText,questionType);
        when(service.getAllQuizzes()).thenReturn(expectedQuiz);
        Iterable<Quiz> result = service.getAllQuizzes();
        ResponseEntity<Void> response = rest.getForEntity(url, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(expectedQuiz, result);
    }
    @Test
    void itShouldCallDeleteQuizFromServiceAndReturnGetAllQuizzes(){

    }

}