package az.code.copart.service;

import az.code.copart.dto.request.FuelTypeCreateRequest;
import az.code.copart.dto.request.FuelTypeUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.FuelTypeCriteria;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.FuelTypeResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.City;
import az.code.copart.entity.FuelType;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.FuelTypeMapper;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.repository.FuelTypeRepository;
import az.code.copart.service.filter.CitySpecification;
import az.code.copart.service.filter.FuelTypeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelTypeService {
    private final FuelTypeRepository fuelTypeRepository;
    private final FuelTypeMapper fuelTypeMapper;
    private final PageableMapper pageableMapper;
    // Add methods to interact with the repository and mapper as needed
    public List<FuelTypeResponse> findAll() {
        return fuelTypeRepository
                .findAll()
                .stream()
                .map(fuelTypeMapper::fromEntityToResponse)
                .toList();
    }

    public PageableResponse<FuelTypeResponse> getAllFuelTypes(Pageable pageable, FuelTypeCriteria criteria) {

        Page<FuelType> all = fuelTypeRepository.findAll(new FuelTypeSpecification(criteria), pageable);
        return pageableMapper.fromFuelTypeEntityToPageableResponse(all);
    }
    public FuelTypeResponse findById(Long id) {
        return fuelTypeRepository
                .findById(id)
                .map(fuelTypeMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .message("FuelType not found with id: " + id)
                        .code(404)
                        .build());
    }
    public FuelTypeResponse saveFuelType(FuelTypeCreateRequest request) {
        if(fuelTypeRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("FuelType already exists")
                    .build();
        }
        return fuelTypeMapper
                .fromEntityToResponse(
                        fuelTypeRepository.save(
                                fuelTypeMapper.fromCreateToEntity(request)
                        ));
    }
    public FuelTypeResponse updateFuelType(FuelTypeUpdateRequest request) {
        if(fuelTypeRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("FuelType already exists")
                    .build();
        }
        FuelType fuelType = fuelTypeRepository.findById(request.getId()).orElseThrow(() -> CustomException.builder()
                .message("FuelType not found with id: " + request.getId())
                .code(404)
                .build());
        return  fuelTypeMapper
                .fromEntityToResponse(
                        fuelTypeRepository.save(
                                (fuelTypeMapper.fromUpdateToEntity(fuelType,request))
                        ));
    }
    public void deleteFuelType(Long id) {
        fuelTypeRepository.deleteById(id);
    }
}
