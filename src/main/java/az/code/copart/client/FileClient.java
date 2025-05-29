package az.code.copart.client;

import az.code.copart.config.FeignClientInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@FeignClient(name = "ms-file", url = "${client.ms-file.endpoint}",configuration = FeignClientInterceptorConfig.class)
public interface FileClient {
    @PostMapping("/upload/{carId}")
    public ResponseEntity<Void> uploadFile(MultipartFile file,
                                           @PathVariable Long carId);
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStream> getFilesByCarId(@PathVariable Long id);
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStream> downloadFile(@PathVariable Long id);
    @GetMapping("/url/{id}")
    public ResponseEntity<String> getFileUrl(@PathVariable Long id);
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id);
    @DeleteMapping("/car/{carId}/all")
    public ResponseEntity<Void> deleteAllFilesByCarId(@PathVariable Long carId);

}

