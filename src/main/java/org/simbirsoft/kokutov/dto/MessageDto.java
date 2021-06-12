package org.simbirsoft.kokutov.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageDto {
    @JsonProperty("message")
    @NotNull
    private String message;
    @JsonProperty("roomName")
    @NotNull
    private String roomName;
}
