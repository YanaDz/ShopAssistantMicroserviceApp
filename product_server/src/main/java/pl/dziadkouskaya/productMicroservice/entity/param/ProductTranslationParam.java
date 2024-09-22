package pl.dziadkouskaya.graphql.entity.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.graphql.entity.enums.Location;
import pl.dziadkouskaya.graphql.entity.enums.TranslationType;

import java.util.Map;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ProductTranslationParam {
    @NonNull
    private TranslationType translationType;
    @NonNull
    private Location location;
    @NonNull
    private String locationTranslation;
    private Map<Location, String> translations;
    private UUID basicLocationId;
}
