package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReportDTO {
    private String startDate;
    private String endDate;

    private int totalShipping;
    private int totalOrder;
    private int totalPending;
    private int totalCompleted;
    private boolean isSearch;
    private boolean isError;

}
