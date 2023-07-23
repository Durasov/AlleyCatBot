package ru.fgs.alleycatbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "point_questions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointQuestionsEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "point_code")
    private String pointCode;
    @Column(name = "point_name")
    private String pointName;
    @Column(name = "point_coordinates")
    private String pointCoordinates;
    @Column(name = "point_question")
    private String pointQuestion;
    @Column(name = "correct_answer")
    private String correctAnswer;
    @Column(name = "next_point")
    private String nextPoint;

}
