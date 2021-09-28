package com.stackroute.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Requirement {
    private String requirementName;
    private String requirementQuantity;
    private String unitOfMeasure;
}
