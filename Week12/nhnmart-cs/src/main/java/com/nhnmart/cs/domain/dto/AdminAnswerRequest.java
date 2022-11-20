package com.nhnmart.cs.domain.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
public class AdminAnswerRequest {
    @NotEmpty
    @Size(max = 40000)
    String answerContent;
}
