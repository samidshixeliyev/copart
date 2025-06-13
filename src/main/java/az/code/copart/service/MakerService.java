package az.code.copart.service;

import az.code.copart.dto.request.MakerCreateRequest;
import az.code.copart.dto.request.MakerUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.MakerCriteria;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.MakerResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.City;
import az.code.copart.entity.Maker;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.MakerMapper;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.repository.MakerRepository;
import az.code.copart.service.filter.CitySpecification;
import az.code.copart.service.filter.MakerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakerService {
    private final MakerRepository makerRepository;
    private final MakerMapper makerMapper;
    private final PageableMapper pageableMapper;
    // Add methods to interact with the repository and mapper as needed
    public List<MakerResponse> findAll() {
        return makerRepository
                .findAll()
                .stream()
                .map(makerMapper::fromEntityToResponse)
                .toList();
    }
    public PageableResponse<MakerResponse> getAllMakers(Pageable pageable, MakerCriteria criteria) {

        Page<Maker> all = makerRepository.findAll(new MakerSpecification(criteria), pageable);
        return pageableMapper.fromMakerEntityToPageableResponse(all);
    }
    public MakerResponse findById(Long id) {
        return makerRepository
                .findById(id)
                .map(makerMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .code(404)
                        .message("Maker not found")
                        .build());
    }
    public MakerResponse saveMaker(MakerCreateRequest request) {
        if(makerRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("Maker already exists")
                    .build();
        }
        return makerMapper
                .fromEntityToResponse(
                        makerRepository.save(
                                makerMapper.fromCreateToEntity(request)
                        ));

    }
    public MakerResponse updateMaker(MakerUpdateRequest request) {
        if(makerRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("Maker already exists")
                    .build();
        }
        Maker maker = makerRepository
                .findById(request.getId())
                .orElseThrow(() -> CustomException.builder()
                .message("Maker not found with id: " + request.getId())
                .code(404)
                .build());
        return  makerMapper
                .fromEntityToResponse(
                        makerRepository.save(
                                (makerMapper.fromUpdateToEntity(maker,request))
                        ));
    }
    public void deleteMaker(Long id) {
        makerRepository.deleteById(id);
    }

}
