package pl.dziadkouskaya.graphql.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;
import pl.dziadkouskaya.graphql.service.FirmService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = FirmController.PATH)
public class FirmController {
    public static final String PATH = "/firm";

    private final FirmService firmService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public FirmDto createFirm(@RequestBody @Valid FirmParams firmParams) {
        return firmService.createFirm(firmParams);
    }

    @GetMapping(produces = "application/json")
    public List<FirmDto> getFirms() {
        return firmService.getAllFirms();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public FirmDto getFirm(@PathVariable("id") UUID id) {
        return firmService.getFirmDtoById(id);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<FirmDto> getFirmsByName(@RequestParam @Valid String name) {
        return firmService.getFirmsByName(name);
    }
}
