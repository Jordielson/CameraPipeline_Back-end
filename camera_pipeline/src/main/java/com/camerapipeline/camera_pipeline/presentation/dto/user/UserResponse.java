package com.camerapipeline.camera_pipeline.presentation.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Integer id;
    private String email;
    private List<String> roles;
}
