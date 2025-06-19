package az.code.copart.service;

import az.code.copart.client.AuthClient;
import az.code.copart.dto.request.CityCreateRequest;
import az.code.copart.dto.request.CityUpdateRequest;
import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.response.CarTypeResponse;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.CarType;
import az.code.copart.entity.City;
import az.code.copart.entity.State;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.CityMapper;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.repository.CityRepository;
import az.code.copart.repository.StateRepository;
import az.code.copart.service.filter.CarTypeSpecification;
import az.code.copart.service.filter.CitySpecification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final StateRepository stateRepository;
    private final PageableMapper pageableMapper;
    private final AuthClient authClient;

    // Add methods to interact with the repository and mapper as needed

    public List<CityResponse> findAll() {
        return cityRepository
                .findAll()
                .stream()
                .map(cityMapper::fromEntityToResponse)
                .toList();
    }

    public PageableResponse<CityResponse> getAllCities(Pageable pageable, CityCriteria criteria) {

        Page<City> all = cityRepository.findAll(new CitySpecification(criteria), pageable);
        return pageableMapper.fromCityEntityToPageableResponse(all);
    }
    public CityResponse findById(Long id) {
        return cityRepository
                .findById(id)
                .map(cityMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .message("City not found with id: " + id)
                        .code(404)
                        .build());
    }
    public CityResponse saveCity(CityCreateRequest request,String token) {
        if (cityRepository.existsByName(request.getName())) {
            throw CustomException.builder()
                    .code(409)
                    .message("City already exists")
                    .build();
        }
        State state = stateRepository.findById(request.getStateId()).orElseThrow(() -> CustomException.builder()
                .message("State not found" + request.getStateId())
                .code(404)
                .build());
        return cityMapper
                .fromEntityToResponse(
                        cityRepository.save(
                                cityMapper.fromCreateToEntity(request, state)
                        ));


    }
    public CityResponse updateCity( CityUpdateRequest request,String token) {
        if (cityRepository.existsByName(request.getName())) {
            throw CustomException.builder()
                    .code(409)
                    .message("City already exists")
                    .build();
        }
        State state = stateRepository.findById(request.getStateId()).orElseThrow(() -> CustomException.builder()
                .message("State not found" + request.getStateId())
                .code(404)
                .build());
        City city = cityRepository.findById(request.getId()).orElseThrow(() -> CustomException.builder()
                .message("City not found with id: " + request.getId())
                .code(404)
                .build());
        return cityMapper.fromEntityToResponse(cityRepository.save(cityMapper.fromUpdateToEntity(city, request, state)));
    }
    public void deleteCity(Long id,String token) {
        cityRepository.deleteById(id);
    }
}
