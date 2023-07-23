package ru.fgs.alleycatbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fgs.alleycatbot.entity.PointQuestionsEntity;

import java.util.Optional;

@Repository
public interface PointQuestionsRepository extends CrudRepository<PointQuestionsEntity, Long> {

    Optional<PointQuestionsEntity> findByPointCode(String pointCode);

}
