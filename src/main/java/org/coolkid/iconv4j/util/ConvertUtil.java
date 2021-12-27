package org.coolkid.iconv4j.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by coolkid on 2021/12/24.
 */

@Slf4j
public class ConvertUtil {
    @SneakyThrows
    public int convert(File file, String output, String code){
        String name = file.getName();
        String input = file.getCanonicalPath();
        String encode = CodeUtil.getEncode(input);
        String out = StringUtils.isBlank(output)? input :output+File.separator+name;
        log.info("开始处理文件:{}, 输出文件:{}, 原文件编码:{}, 目标编码:{}", input, out, encode, code);
        if(code.equalsIgnoreCase(encode)){
            if(!input.equals(out)){
                FileUtils.copyFile(file, new File(out));
                log.info("文件编码相同, 复制文件 输入:{} 输出:{}", input, out);
            }else{
                log.info("文件编码相同, 跳过:{}", input);
            }
        }else{
            FileUtil.writeFile(input, out, Charset.forName(encode), Charset.forName(code));
            log.info("完成输入:{} 输出:{}", input, out);
        }
        return 1;
    }
}
