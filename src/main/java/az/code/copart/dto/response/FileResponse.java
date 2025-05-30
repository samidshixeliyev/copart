package az.code.copart.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FileResponse implements Serializable {
    private Long Id;
    private Long carId;
    private String contentType;
    private String imagePath;
    private String imageName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
