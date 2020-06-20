package com.caionastu.core.error;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private String domain;
    private String message;
}
