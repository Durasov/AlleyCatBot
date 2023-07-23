package ru.fgs.alleycatbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import ru.fgs.alleycatbot.dto.UserVisitedPointsDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_visited_points")
@NamedNativeQuery(
        name = "find_user_visited_points",
        query = """
        select u.user_name as userName, pq.id as pointId, pq.point_name as pointName, uvp.visited_time as visitedTime
        from user u, point_questions pq, user_visited_points uvp
        where u.id = uvp.user_id
          and pq.point_code = uvp.user_visited_point_code;
        """,
        resultSetMapping = "user_visited_points_dto"
)
@SqlResultSetMapping(
        name = "user_visited_points_dto",
        classes = @ConstructorResult(
                targetClass = UserVisitedPointsDto.class,
                columns = {
                        @ColumnResult(name = "userName", type = String.class),
                        @ColumnResult(name = "pointId", type = Long.class),
                        @ColumnResult(name = "pointName", type = String.class),
                        @ColumnResult(name = "visitedTime", type = LocalDateTime.class)
                }
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVisitedPointsEntity {

    public UserVisitedPointsEntity(Long userId, String userVisitedPointCode) {
        this.userId = userId;
        this.userVisitedPointCode = userVisitedPointCode;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_visited_point_code")
    private String userVisitedPointCode;
    @CurrentTimestamp
    @Column(name = "visited_time")
    private LocalDateTime visitedTime;

}
