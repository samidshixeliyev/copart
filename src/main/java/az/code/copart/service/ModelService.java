package az.code.copart.service;

import az.code.copart.dto.request.ModelCreateRequest;
import az.code.copart.dto.request.ModelUpdateRequest;
import az.code.copart.dto.response.ModelResponse;
import az.code.copart.entity.Maker;
import az.code.copart.entity.Model;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.ModelMapper;
import az.code.copart.repository.MakerRepository;
import az.code.copart.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final MakerRepository makerRepository;

    // Add methods to interact with the repository and mapper as needed
    public List<ModelResponse> getAllModels() {
        return modelRepository.findAll()
                .stream()
                .map(modelMapper::fromEntityToResponse)
                .toList();
    }
    public ModelResponse getModelById(Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> CustomException.builder()
                .message("Model not found with id: " + id)
                .code(404)
                .build());
        return modelMapper.fromEntityToResponse(model);
    }
    public ModelResponse saveModel(ModelCreateRequest request) {
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
    public ModelResponse updateModel(ModelUpdateRequest request) {
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
    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }
}
