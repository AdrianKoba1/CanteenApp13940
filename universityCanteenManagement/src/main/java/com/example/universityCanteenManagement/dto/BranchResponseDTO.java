package com.example.universityCanteenManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private String contactInfo;
}
