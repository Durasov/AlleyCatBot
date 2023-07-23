package ru.fgs.alleycatbot.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fgs.alleycatbot.dto.UserVisitedPointsDto;
import ru.fgs.alleycatbot.entity.UserVisitedPointsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVisitedPointsRepository extends CrudRepository<UserVisitedPointsEntity, Long> {

    Optional<UserVisitedPointsEntity> findByUserIdAndUserVisitedPointCode(Long userId, String userVisitedPointCode);

    @Query(name = "find_user_visited_points", nativeQuery = true)
    List<UserVisitedPointsDto> findAllUsersVisitedPoints();

    @Modifying
    @Query(value = "delete from user_visited_points where user_id = :userId", nativeQuery = true)
    void deleteByUserId(Long userId);

}
