package com.nhnmart.cs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginRequest {
    @NotEmpty
    String id;
    @NotEmpty
    String password;
}
