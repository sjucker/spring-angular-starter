package ch.stefanjucker.dto;

import javax.validation.constraints.NotNull;

public record ChangePasswordRequestDTO(@NotNull String oldPassword, @NotNull String newPassword) {
}
