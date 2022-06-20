package net.yorksolutions.quiz;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long quizTemplateId;
    private int questionNumber;
    private String questionType;
    private String quizQuestion;

    public Quiz(){

    }
    public Quiz(Long quizTemplateId, int questionNumber, String quizQuestion, String questionType){
       this.quizTemplateId = quizTemplateId;
       this.questionNumber = questionNumber;
       this.quizQuestion = quizQuestion;
       this.questionType = questionType;
    }

    public Long getId() {
        return id;
    }

    public Long getQuizTemplateId() {
        return quizTemplateId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setQuizTemplateId(Long quizTemplateId) {
        this.quizTemplateId = quizTemplateId;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return questionNumber == quiz.questionNumber && Objects.equals(id, quiz.id) && Objects.equals(quizTemplateId, quiz.quizTemplateId) && Objects.equals(questionType, quiz.questionType) && Objects.equals(quizQuestion, quiz.quizQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizTemplateId, questionNumber, questionType, quizQuestion);
    }
}