package com.nhnmart.cs.domain.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class AdminSearchCategoryRequest {
    @NotEmpty
    String category;
}
