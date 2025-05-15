package az.code.copart.util;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class FileUtil {
    private static final String DATE_FORMAT = "yyyyMMdd_HHmmss_SSS";
    private static final String FILE_NAME_SEPARATOR = "_";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private FileUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String generateUniqueFileName(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            throw new IllegalArgumentException("Original file name cannot be null or empty");
        }

        return String.join(FILE_NAME_SEPARATOR,
                getCurrentTimestamp(),
                generateUUID(),
                originalFileName);
    }

    public static String getMimeType(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        return MediaTypeFactory.getMediaType(fileName)
                .map(MediaType::toString)
                .orElse(null);
    }

    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
