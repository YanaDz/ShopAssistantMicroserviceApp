package pl.dziadkouskaya.graphql.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;
import pl.dziadkouskaya.graphql.service.FirmService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = FirmController.PATH)
public class FirmController {
    public static final String PATH = "/firm";

    private final FirmService firmService;

    @PostMapping
    public FirmDto createFirm(@RequestBody FirmParams firmParams) {
       return firmService.createFirm(firmParams);

    }
}
