package az.code.copart.service;

import az.code.copart.dto.request.StateCreateRequest;
import az.code.copart.dto.request.StateUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.StateCriteria;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.dto.response.StateResponse;
import az.code.copart.entity.City;
import az.code.copart.entity.State;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.mapper.StateMapper;
import az.code.copart.repository.StateRepository;
import az.code.copart.service.filter.CitySpecification;
import az.code.copart.service.filter.StateSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {
    private final StateRepository stateRepository;
    private final StateMapper stateMapper;
    private final PageableMapper pageableMapper;
    // Add methods to interact with the repository and mapper as needed
    public List<StateResponse> getAllStates() {
        return stateRepository.findAll()
                .stream()
                .map(stateMapper::fromEntityToResponse)
                .toList();
    }
    public PageableResponse<StateResponse> getAllStates(Pageable pageable, StateCriteria criteria) {

        Page<State> all = stateRepository.findAll(new StateSpecification(criteria), pageable);
        return pageableMapper.fromStateEntityToPageableResponse(all);
    }
    public StateResponse getStateById(Long id) {
        return stateRepository.findById(id)
                .map(stateMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .message("State not found"+id)
                        .code(404)
                        .build());
    }
    public StateResponse saveState(StateCreateRequest request) {
        if(stateRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("State already exists")
                    .build();
        }
        return stateMapper.fromEntityToResponse(
                stateRepository.save(
                        stateMapper.fromCreateToEntity(request)
                ));
    }
    public StateResponse updateState(StateUpdateRequest request) {
        State state = stateRepository
                .findById(request.getId())
                .orElseThrow(()->CustomException.builder()
                        .code(404)
                        .message("State not found" + request.getId())
                        .build());
        return stateMapper.fromEntityToResponse(
                stateRepository.save(
                        stateMapper.fromUpdateToEntity(state,request)
                ));
    }
    public void deleteState(Long id) {
        stateRepository.deleteById(id);
    }
}
