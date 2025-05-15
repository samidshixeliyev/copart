package az.code.copart.dto.response;

import az.code.copart.entity.Car;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CarImageResponse implements Serializable {
    private Long Id;
    private String imagePath;
    private String imageName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
