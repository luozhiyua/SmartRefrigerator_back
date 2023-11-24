package com.github.angel.raa.modules.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    @NotBlank(message = "question")
    private String question;
    @NotBlank(message = "answer")
    private String answer;
}

