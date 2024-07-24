package pl.dziadkouskaya.graphql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = TranslationController.PATH)
public class TranslationController {
    public static final String PATH = "/translation";
}
