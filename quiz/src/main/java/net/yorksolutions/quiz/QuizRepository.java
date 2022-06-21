package net.yorksolutions.quiz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
    //Optional<Quiz> findByQuizTemplateId(Long quizTemplateId);
    @Transactional
    Iterable<Quiz> deleteAllByQuizTemplateId(Long quizTemplateId);
    Iterable<Quiz> findAllByQuizTemplateIdAscQuestionNumberAsc();

}
