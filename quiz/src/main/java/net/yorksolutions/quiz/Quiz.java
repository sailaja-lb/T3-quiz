package net.yorksolutions.quiz;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.implementation.bind.annotation.AllArguments;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long questionId;

    private Long quizTemplateId;
    private int questionNumber;
    private String questionType;
    private String questionText;

    public Quiz(){

    }
    public Quiz(Long quizTemplateId, int questionNumber, String questionText, String questionType){
       this.quizTemplateId = quizTemplateId;
       this.questionNumber = questionNumber;
       this.questionText = questionText;
       this.questionType = questionType;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizTemplateId() {
        return quizTemplateId;
    }

    public void setQuizTemplateId(Long quizTemplateId) {
        this.quizTemplateId = quizTemplateId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return questionNumber == quiz.questionNumber && Objects.equals(questionId, quiz.questionId) && Objects.equals(quizTemplateId, quiz.quizTemplateId) && Objects.equals(questionType, quiz.questionType) && Objects.equals(questionText, quiz.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, quizTemplateId, questionNumber, questionType, questionText);
    }
}
