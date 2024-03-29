package com.backend.hyunfit.domain.pt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeslotDTO {
    private String trnId;
    private List<String> days;
    private List<String> timeslots;
    public static TimeslotDTO empty(String trnId) {
        return new TimeslotDTO(trnId, new ArrayList<>(), null);
    }
}
