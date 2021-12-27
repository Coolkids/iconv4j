package org.coolkid.iconv4j.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by coolkid on 2021/12/24.
 */

@Slf4j
public class FileUtil {
    private FileUtil() {
    }

    @SneakyThrows
    public static List<File> getFiles(String path) {
        String[] filter = {"sql", "txt", "ass", "srt", "ssa"};
        return getFiles(path, filter);
    }

    @SneakyThrows
    public static List<File> getFiles(String path, String[] filter) {
        Collection<File> files;
        if (filter == null) {
            files = FileUtils.listFiles(new File(path), null, true);
        } else {
            files = FileUtils.listFiles(new File(path), filter, true);
        }
        if (CollectionUtils.isNotEmpty(files)) {
            return files.stream().filter(Objects::nonNull).filter(File::exists).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static List<File> getFiles(String path, String filter) {
        String[] filters = StringUtils.split(filter, ",");
        return getFiles(path, filters);
    }

    public static void writeFile(String orig, String dist, Charset origCharset, Charset distCharset) {
        List<String> lines = new ArrayList<>();
        try (FileInputStream input = new FileInputStream(orig)) {
            lines = IOUtils.readLines(input, origCharset);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        if (CollectionUtils.isEmpty(lines)) {
            log.info("源文件为空");
            return;
        }
        //处理UTF-8 BOM
        if (StandardCharsets.UTF_8.equals(origCharset) && lines.get(0).substring(0, 1).contains("\uFEFF")) {
            lines.set(0, lines.get(0).substring(1));
        }
        try (FileOutputStream output = new FileOutputStream(dist)) {
            IOUtils.writeLines(lines, null, output, distCharset);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
