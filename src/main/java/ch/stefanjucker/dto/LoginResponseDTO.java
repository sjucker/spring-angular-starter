package ch.stefanjucker.dto;

import javax.validation.constraints.NotNull;

public record LoginResponseDTO(@NotNull Long id, @NotNull String name, @NotNull String jwt) {
}
