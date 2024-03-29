package com.twinkle.JakSim.model.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private int reviewId;
    private String userId;
    private int trainerId;
    private String reviewContent;
    private int star;
    private String reviewCreateDate;
    private String reviewModifyDate;
    private double avgRstar;
    private int reviewCnt;

}
