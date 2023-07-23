package ru.fgs.alleycatbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name="id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_points")
    private Integer userPoints;

}
