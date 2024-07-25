package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.graphql.entity.param.ProductTranslationParam;
import pl.dziadkouskaya.graphql.exception.ResourceExistedException;
import pl.dziadkouskaya.graphql.exception.ResourceNotFoundException;
import pl.dziadkouskaya.graphql.mapper.ProductTranslationMapper;
import pl.dziadkouskaya.graphql.repository.sql.ProductTranslationRepository;
import pl.dziadkouskaya.graphql.service.ProductTranslationService;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static pl.dziadkouskaya.graphql.entity.enums.TranslationType.MAIN;

@Service
@Slf4j
public class ProductTranslationServiceImpl implements ProductTranslationService {
    private final ProductTranslationRepository repository;
    private final ProductTranslationMapper mapper;

    public ProductTranslationServiceImpl(ProductTranslationRepository productTranslationRepository, ProductTranslationMapper mapper) {
        this.repository = productTranslationRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductTranslationDto createProductTranslation(ProductTranslationParam param) {
        var existedEntity = repository.getTranslationByStrictName(param.getLocationTranslation(), param.getLocation());
        if (!existedEntity.isEmpty()) {
            throw new ResourceExistedException(String.format("Translation with name %s existed.", param.getLocationTranslation()));
        }
        var initialTranslation = mapper.toEntity(param);
        if (param.getTranslationType() == MAIN
                && (isNull(param.getTranslations())) || param.getLocationTranslation().isEmpty()) {
            var translation = repository.save(initialTranslation);
            return mapper.toDto(translation);
        }
        if (param.getTranslationType() == MAIN) {
            var transtation = mapper.toDto(repository.save(initialTranslation));
            var additionalTranslations = param.getTranslations().entrySet().stream()
                    .map(entry -> {
                                var additionalTranslation = mapper.toAdditionalTranslation(transtation.getId(), entry.getKey(),
                                        entry.getValue());
                                additionalTranslation = repository.save(additionalTranslation);
                                var dto = mapper.toDto(additionalTranslation);
                                return dto;
                            }
                    ).toList();
            transtation.setAdditionalTranslations(additionalTranslations);
            return transtation;
        }
        var basicTranslation = repository.findById(param.getBasicLocationId());
        if (basicTranslation.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Basic entity with id %s doesn't exist", param.getBasicLocationId()));
        }
        var additionalTranslation = mapper.toAdditionalTranslation(param.getBasicLocationId(), param.getLocation(), param.getLocationTranslation());
        var savedTranslaton = repository.save(additionalTranslation);
        return mapper.toDto(savedTranslaton, mapper.toDto(basicTranslation.get()));
    }

    @Override
    public List<ProductTranslationDto> getTranslations() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductTranslationDto getTranslation(UUID id) {
        var translation = repository.findById(id);
        if (translation.isEmpty()) {
            throw new ResourceNotFoundException(String.format("There is no slation with id %s.", id));
        }
        return mapper.toDto(translation.get());
    }

    @Override
    public List<ProductTranslationDto> getTranslationsByName(String name) {
        return repository.getTranslationByName(name).stream()
                .map(mapper::toDto)
                .toList();
    }

}
