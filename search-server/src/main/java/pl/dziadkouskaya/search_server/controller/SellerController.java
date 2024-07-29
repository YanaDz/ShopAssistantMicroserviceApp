package pl.dziadkouskaya.search_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;
import pl.dziadkouskaya.search_server.service.SellerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SellerController.PATH)
public class SellerController {
    public static final String PATH = "/seller";

    @Autowired
    private final SellerService sellerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Seller createFirm(@RequestBody SellerParam sellerParam) {
        return sellerService.createSeller(sellerParam);
    }

    @GetMapping(produces = "application/json")
    public List<Seller> getFirms() {
        return sellerService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Seller getFirm(@PathVariable("id") UUID id) {
        return sellerService.getById(id);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<Seller> getFirmsByLocation(@RequestParam Location location) {
        return sellerService.getSellers(location);
    }

}

