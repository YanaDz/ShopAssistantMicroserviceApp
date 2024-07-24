package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;
import pl.dziadkouskaya.graphql.exception.ResourceNotFoundException;
import pl.dziadkouskaya.graphql.mapper.FirmMapper;
import pl.dziadkouskaya.graphql.repository.sql.FirmRepository;
import pl.dziadkouskaya.graphql.service.FirmService;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class FirmServiceImpl implements FirmService {
    private final FirmRepository firmRepository;
    private final FirmMapper firmMapper;

    public FirmServiceImpl(FirmRepository firmRepository, FirmMapper firmMapper) {
        this.firmRepository = firmRepository;
        this.firmMapper = firmMapper;
    }

    @Override
    public FirmDto createFirm(FirmParams firmParams) {
        log.info("Create firm with name {}", firmParams.getName());
        var initialFirm = firmMapper.toEntity(firmParams);
        var firm = firmRepository.save(initialFirm);
        log.info("Firm with id {} is created.", firm.getId());
        return firmMapper.toDto(firm);
    }

    @Override
    public List<FirmDto> getAllFirms() {
        var firms =  firmRepository.findAll();
        log.info("Get {} firms.", firms.size());
        return firms.stream()
                .map(firmMapper::toDto)
                .toList();
    }

    @Override
    public FirmDto getFirmById(UUID uuid) {
        var firm = firmRepository.findById(uuid);
        if (firm.isEmpty()) {
            throw new ResourceNotFoundException("There is firm with id " + uuid);
        }
        return firmMapper.toDto(firm.get());
    }

    @Override
    public List<FirmDto> getFirmsByName(String name) {
        log.info("Find firm by name {}.", name);
        var firms = firmRepository.findFirmByName(name);
        if (firms.isEmpty()) {
            throw new ResourceNotFoundException("There is firm with name " + name);
        }
        log.info("Found {} firms with name {}.", name);
        return firms.stream()
                .map(firmMapper::toDto)
                .toList();
    }
}
