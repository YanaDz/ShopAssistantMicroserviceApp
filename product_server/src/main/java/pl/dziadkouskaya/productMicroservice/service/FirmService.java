package pl.dziadkouskaya.productMicroservice.service;

import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmParams;

import java.util.List;
import java.util.UUID;

public interface FirmService {
    public FirmDto createFirm(FirmParams firmParams);
    List<FirmDto> getAllFirms();
    FirmDto getFirmDtoById(UUID uuid);
    List<FirmDto> getFirmsByName(String name);
    Firm getFirmById(UUID uuid);

}
