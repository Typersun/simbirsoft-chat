package org.simbirsoft.kokutov.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Validated
public class MessageDto {
    @JsonProperty("message")
    @NotNull

    private String message;
    @JsonProperty("roomName")
    @NotNull
    private String roomName;
}
