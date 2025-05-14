package az.code.copart.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtil {

    private static final String DATE_FORMAT = "yyyyMMdd_HHmmss_SSS";

    public static String generateUniqueFileName(String fileName) {
        String timestamp = getCurrentTimestamp();
        String uuid = UUID.randomUUID().toString();
        return timestamp + "_" + uuid + "_" + fileName;
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }
}