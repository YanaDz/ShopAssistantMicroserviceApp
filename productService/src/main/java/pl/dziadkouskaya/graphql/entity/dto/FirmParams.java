package pl.dziadkouskaya.graphql.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


import static pl.dziadkouskaya.graphql.utils.Constants.FIRM_NAME_NOT_EMPTY;
import static pl.dziadkouskaya.graphql.utils.Constants.FIRM_NAME_NOT_NULL;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class FirmParams {
    @NotNull(message = FIRM_NAME_NOT_NULL)
    @NotEmpty(message = FIRM_NAME_NOT_EMPTY)
    private String name;

}
