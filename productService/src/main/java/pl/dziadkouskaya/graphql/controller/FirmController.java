package pl.dziadkouskaya.graphql.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = FirmController.PATH)
public class FirmController {
    public static final String PATH = "/firm";

    @PostMapping
    public FirmDto createFirm(FirmParams firmParams) {
return null;

    }
}
