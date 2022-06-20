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
    private Long quizId;
//    {quizTemplateId:1,questionNumber;2,questionType:"string", quizQuestion:"string"}
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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return questionNumber == quiz.questionNumber && Objects.equals(quizId, quiz.quizId) && Objects.equals(quizTemplateId, quiz.quizTemplateId) && Objects.equals(questionType, quiz.questionType) && Objects.equals(quizQuestion, quiz.quizQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, quizTemplateId, questionNumber, questionType, quizQuestion);
    }
}
