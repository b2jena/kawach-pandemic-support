package com.stackroute.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This is a Model class, this contains Requirement Name, RequirementQuantity, Unit of Measures
 * the SOS Request Data will be stored in this format.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Requirement {
    private String strRequirementName;
    private String strRequirementQuantity;
    private String strUnitOfMeasure;
}
