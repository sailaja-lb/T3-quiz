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
    private String question;

    public Quiz(){

    }
    public Quiz(Long quizTemplateId, int questionNumber, String question, String questionType){
       this.quizTemplateId = quizTemplateId;
       this.questionNumber = questionNumber;
       this.question = question;
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

    public String getQuestion() {
        return question;
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

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return questionNumber == quiz.questionNumber && Objects.equals(id, quiz.id) && Objects.equals(quizTemplateId, quiz.quizTemplateId) && Objects.equals(questionType, quiz.questionType) && Objects.equals(question, quiz.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizTemplateId, questionNumber, questionType, question);
    }
}
