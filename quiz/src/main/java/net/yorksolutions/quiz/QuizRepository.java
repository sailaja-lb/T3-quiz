package net.yorksolutions.quiz;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    
    @Override
    Optional<Quiz> findById(Long aLong);
}
