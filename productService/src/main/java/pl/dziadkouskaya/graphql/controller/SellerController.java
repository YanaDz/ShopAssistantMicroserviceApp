package pl.dziadkouskaya.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dziadkouskaya.graphql.entity.dto.SellerDto;
import pl.dziadkouskaya.graphql.entity.dto.SellerParam;
import pl.dziadkouskaya.graphql.service.SellerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SellerController.PATH)
public class SellerController {
    public static final String PATH = "/seller";

    private final SellerService sellerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public SellerDto createFirm(@RequestBody SellerParam sellerParam) {
        return sellerService.createSeller(sellerParam);
    }

    @GetMapping(produces = "application/json")
    public List<SellerDto> getFirms() {
        return sellerService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public SellerDto getFirm(@PathVariable("id") UUID id) {
        return sellerService.getById(id);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<SellerDto> getFirmsByName(@RequestParam String name) {
        return sellerService.getSellersByName(name);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public SellerDto updateSeller(@PathVariable UUID id, @RequestBody SellerParam param) {
        return sellerService.updateSeller(id, param);
    }
}

