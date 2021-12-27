package org.coolkid.iconv4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.coolkid.iconv4j.util.ConvertUtil;
import org.coolkid.iconv4j.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by coolkid on 2021/12/24.
 */

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRun {
    private String input = "";
    private String output = "";
    private String code = "";
    private String filter = "";
    private ConvertUtil convertUtil;

    public void run() {
        List<File> files = FileUtil.getFiles(input, filter);
        log.info("文件数量:{}", CollectionUtils.size(files));
        ExecutorService executor = Executors.newFixedThreadPool(10);
        files.forEach(t -> executor.submit(() -> convertUtil.convert(t, output, code)));
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
