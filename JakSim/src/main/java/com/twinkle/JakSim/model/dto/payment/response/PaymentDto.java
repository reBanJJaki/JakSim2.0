package com.twinkle.JakSim.model.dto.payment.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@ToString
public class PaymentDto {

    private int pIdx;

    private String userId;

    private int tpIdx;

    private LocalDate pCDt;

    private int pRefund;

    private LocalDate pMDt;

    private int pPtCnt;

}