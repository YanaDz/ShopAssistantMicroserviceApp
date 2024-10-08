package pl.dziadkouskaya.productMicroservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmParams;
import pl.dziadkouskaya.productMicroservice.exception.ResourceNotFoundException;
import pl.dziadkouskaya.productMicroservice.mapper.FirmMapper;
import pl.dziadkouskaya.productMicroservice.repository.sql.FirmRepository;
import pl.dziadkouskaya.productMicroservice.service.FirmService;

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
    public FirmDto getFirmDtoById(UUID uuid) {
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
        log.info("Found {} firms with name {}.", firms.size(), name);
        return firms.stream()
                .map(firmMapper::toDto)
                .toList();
    }

    @Override
    public Firm getFirmById(UUID uuid) {
        var firm = firmRepository.findById(uuid);
        if (firm.isEmpty()) {
            throw new ResourceNotFoundException("There is firm with id " + uuid);
        }
        return firm.get();
    }
}
