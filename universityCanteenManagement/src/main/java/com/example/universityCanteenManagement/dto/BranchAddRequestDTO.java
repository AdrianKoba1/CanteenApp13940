package com.example.universityCanteenManagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchAddRequestDTO {

    private String name;
    private String description;
    private String location;
    private String contactInfo;

}
