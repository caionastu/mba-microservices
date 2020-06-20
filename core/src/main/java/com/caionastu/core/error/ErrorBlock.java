package com.caionastu.core.error;


import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Set;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBlock {

    @Getter
    @Setter
    private HttpStatus code;

    @Getter
    @Setter
    private String header;

    @Builder.Default
    private Set<ErrorMessage> errorMessages = Sets.newHashSet();

    public Set<ErrorMessage> getErrorMessages() {
        return Collections.unmodifiableSet(errorMessages);
    }

    public void addErrorMessage(String message) {
        errorMessages.add(ErrorMessage.builder().message(message).build());
    }

    public void addErrorMessages(Set<ErrorMessage> errorMessages) {
        this.errorMessages.addAll(errorMessages);
    }

    public boolean hasErrors() {
        return !errorMessages.isEmpty();
    }
}
