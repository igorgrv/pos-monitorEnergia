package graduate.energymonitor.controller.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import graduate.energymonitor.entity.User;
import graduate.energymonitor.entity.enums.GenderEnum;
import graduate.energymonitor.entity.enums.RelativesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "UserDTO", description = "Object that represents a data transfer object for a user")
public record UserDto(
    
        @NotBlank(message = "name is mandatory")
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
        @Schema(description = "Name to identify an user", example = "Igor Romero")
        String name,

        @NotNull(message = "birth is mandatory") @DateTimeFormat(pattern = "YYYY-MM-dd") @Past LocalDate birth,
        @NotNull(message = "gender is mandatory") GenderEnum gender,
        @NotNull(message = "relative is mandatory") RelativesEnum relative) {

    public User toUser() {
        return new User(name, birth, gender, relative);
    }

}
