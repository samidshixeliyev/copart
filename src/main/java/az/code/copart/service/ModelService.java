package az.code.copart.service;

import az.code.copart.client.AuthClient;
import az.code.copart.dto.request.ModelCreateRequest;
import az.code.copart.dto.request.ModelUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.ModelCriteria;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.ModelResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.City;
import az.code.copart.entity.Maker;
import az.code.copart.entity.Model;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.ModelMapper;
import az.code.copart.mapper.PageableMapper;
import az.code.copart.repository.MakerRepository;
import az.code.copart.repository.ModelRepository;
import az.code.copart.service.filter.CitySpecification;
import az.code.copart.service.filter.ModelSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.Security;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final MakerRepository makerRepository;
    private final PageableMapper pageableMapper;
    private final AuthClient authClient;
    // Add methods to interact with the repository and mapper as needed
    public List<ModelResponse> getAllModels() {
        return modelRepository.findAll()
                .stream()
                .map(modelMapper::fromEntityToResponse)
                .toList();
    }

    public PageableResponse<ModelResponse> getAllModels(Pageable pageable, ModelCriteria criteria) {

        Page<Model> all = modelRepository.findAll(new ModelSpecification(criteria), pageable);
        return pageableMapper.fromModelEntityToPageableResponse(all);
    }

    public ModelResponse getModelById(Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> CustomException.builder()
                .message("Model not found with id: " + id)
                .code(404)
                .build());
        return modelMapper.fromEntityToResponse(model);
    }

    public ModelResponse saveModel( ModelCreateRequest request,  String token) {
        authClient.editableUser(token);
        if(modelRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("Model already exists")
                    .build();
        }
        Maker maker = makerRepository.findById(request.getMakerId()).orElseThrow(() -> CustomException.builder()
                .message("Maker not found with id: " + request.getMakerId())
                .code(404)
                .build());

        return modelMapper.fromEntityToResponse(
                modelRepository.save(
                        modelMapper.fromCreateToEntity(request,maker)
                ));
    }

    public ModelResponse updateModel(ModelUpdateRequest request, String token) {
        authClient.editableUser(token);
        if(modelRepository.existsByName(request.getName())){
            throw CustomException.builder()
                    .code(409)
                    .message("Model already exists")
                    .build();
        }
        Maker maker = makerRepository.findById(request.getMakerId()).orElseThrow(() -> CustomException.builder()
                .message("Maker not found with id: " + request.getMakerId())
                .code(404)
                .build());
        Model model = modelRepository.findById(request.getId()).orElseThrow(() -> CustomException.builder()
                .message("Model not found with id: " + request.getId())
                .code(404)
                .build());
      return   modelMapper
              .fromEntityToResponse(
                      modelRepository.save(
                              modelMapper.fromUpdateToEntity(model,request,maker)
                      ));
    }

    public void deleteModel(Long id,String token) {
        authClient.editableUser(token);
        modelRepository.deleteById(id);
    }
}
