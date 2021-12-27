package org.coolkid.iconv4j.util;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by coolkid on 2021/12/24.
 */

@Slf4j
public class CodeUtil {
    private CodeUtil(){

    }

    public static String getEncode(byte[] bytes) {
        CharsetDetector detector = new CharsetDetector();
        detector.setText(bytes);
        CharsetMatch match = detector.detect();
        return match.getName();
    }

    private static byte[] readFileBytes(String filename) {
        try {
            return FileUtils.readFileToByteArray(new File(filename));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
        return new byte[0];
    }

    public static String getEncode(String filename) {
        return getEncode(readFileBytes(filename));
    }
}
