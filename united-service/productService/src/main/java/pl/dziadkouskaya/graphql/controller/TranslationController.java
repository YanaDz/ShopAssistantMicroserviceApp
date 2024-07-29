package pl.dziadkouskaya.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dziadkouskaya.graphql.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.graphql.entity.param.ProductTranslationParam;
import pl.dziadkouskaya.graphql.service.ProductTranslationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = TranslationController.PATH)
public class TranslationController {
    public static final String PATH = "/translation";

    private final ProductTranslationService productTranslationService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ProductTranslationDto createProductTranslation(@RequestBody ProductTranslationParam param) {
        return productTranslationService.createProductTranslation(param);
    }

    @GetMapping(consumes = "application/json")
    public List<ProductTranslationDto> getTranslations() {
        return productTranslationService.getTranslations();
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public ProductTranslationDto getProductTranslation(@PathVariable UUID id) {
        return productTranslationService.getTranslation(id);
    }

    @GetMapping(value = "/search", consumes = "application/json")
    public List<ProductTranslationDto> getProductTranslationByName(@RequestParam String name) {
        return productTranslationService.getTranslationsByName(name);
    }
}
