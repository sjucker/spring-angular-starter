package ch.stefanjucker.dto;

import javax.validation.constraints.NotNull;

public record LoginRequestDTO(@NotNull String email, @NotNull String password) {
}
