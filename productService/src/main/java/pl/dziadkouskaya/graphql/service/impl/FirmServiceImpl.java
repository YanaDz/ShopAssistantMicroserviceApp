package pl.dziadkouskaya.graphql.service.impl;

import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;
import pl.dziadkouskaya.graphql.repository.FirmRepository;
import pl.dziadkouskaya.graphql.service.FirmService;

import java.util.List;
import java.util.UUID;

public class FirmServiceImpl implements FirmService {
    private final FirmRepository firmRepository;

    public FirmServiceImpl(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    @Override
    public FirmDto createFirm(FirmParams firmParams) {
        return null;
    }

    @Override
    public List<FirmDto> getAllFirms() {
        return List.of();
    }

    @Override
    public FirmDto getFirmById(UUID uuid) {
        return null;
    }

    @Override
    public List<FirmDto> getFirmsByName(String name) {
        return List.of();
    }
}
