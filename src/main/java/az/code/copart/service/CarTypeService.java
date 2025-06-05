package az.code.copart.service;

import az.code.copart.dto.request.CarTypeCreateRequest;
import az.code.copart.dto.request.CarTypeUpdateRequest;
import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.response.CarTypeResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.CarType;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.CarTypeMapper;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.repository.CarTypeRepository;
import az.code.copart.service.filter.CarTypeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarTypeService {
    private final CarTypeRepository carTypeRepository;
    private final CarTypeMapper carTypeMapper;
    private final PageableMapper pageableMapper;
    // Add methods to interact with the repository and mapper as needed
    public List<CarTypeResponse> getAllCarTypes() {
        return carTypeRepository.findAll()
                .stream()
                .map(carTypeMapper::fromEntityToResponse)
                .toList();
    }

    public PageableResponse<CarTypeResponse> getAllCarTypes(Pageable pageable, CarTypeCriteria criteria) {

        Page<CarType> all = carTypeRepository.findAll(new CarTypeSpecification(criteria), pageable);
        return pageableMapper.fromCarTypeEntityToPageableResponse(all);


    }
    public CarTypeResponse getCarTypeById(Long id) {
        return carTypeRepository.findById(id)
                .map(carTypeMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .message("CarType not found with id: " + id)
                        .code(404)
                        .build());
    }
    public CarTypeResponse saveCarType(CarTypeCreateRequest request) {
        if(carTypeRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("CarType already exists")
                    .build();
        }
        return carTypeMapper.fromEntityToResponse(
                carTypeRepository.save(
                        carTypeMapper.fromCreateToEntity(request)
                ));
    }
    public CarTypeResponse updateCarType(CarTypeUpdateRequest request) {
        if(carTypeRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("CarType already exists")
                    .build();
        }
        CarType carType = carTypeRepository.findById(request.getId())
                .orElseThrow(() -> CustomException.builder()
                        .message("CarType not found with id: " + request.getId())
                        .code(404)
                        .build());
        carTypeMapper.fromUpdateToEntity(carType,request);
        return carTypeMapper.fromEntityToResponse(carTypeRepository.save(carType));
    }
    public void deleteCarType(Long id) {
        carTypeRepository.deleteById(id);
    }
}
