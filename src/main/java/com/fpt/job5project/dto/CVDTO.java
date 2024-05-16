package com.fpt.job5project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVDTO {
    private long cvId;
    private long candidateId;
    private String cvFile;
    private String cvName;
    private String description;
    private boolean mainCV;

}
