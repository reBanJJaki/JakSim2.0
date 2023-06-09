package com.twinkle.JakSim.model.dto.reservation.request;

import lombok.*;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class ReservationDto {

    @NotBlank
    @Size(max = 8)
    private int pIdx;

    @NotBlank
    @Size(max = 8)
    private int tIdx;

    @NotBlank
    private LocalDate tDate;
}