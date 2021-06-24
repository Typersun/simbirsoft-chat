package org.simbirsoft.kokutov.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageMetricsDto {
    private String message;
    private String author;
}
