package net.yorksolutions.quiz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {

    Optional<Quiz> findById(Long id);
}
