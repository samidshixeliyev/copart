package az.code.copart.client;

import az.code.copart.config.FeignClientInterceptorConfig;
import az.code.copart.dto.response.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@FeignClient(name = "ms-file", url = "${client.ms-file.endpoint}",configuration = FeignClientInterceptorConfig.class)
public interface FileClient {
    @PostMapping(value = "/file/upload/{carId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@RequestPart("file") MultipartFile file, @PathVariable Long carId);
    @GetMapping("/file/car/{id}")
    public ResponseEntity<List<FileResponse>> getFilesByCarId(@PathVariable Long id);
    @GetMapping("/file/download/{id}")
    public ResponseEntity<InputStream> downloadFile(@PathVariable Long id);
    @GetMapping("/file/url/{id}")
    public ResponseEntity<String> getFileUrl(@PathVariable Long id);
    @DeleteMapping("/file/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id);
    @DeleteMapping("/file/car/{carId}/all")
    public ResponseEntity<Void> deleteAllFilesByCarId(@PathVariable Long carId);
}

