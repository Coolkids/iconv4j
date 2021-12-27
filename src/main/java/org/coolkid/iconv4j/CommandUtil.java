package org.coolkid.iconv4j;
import org.coolkid.iconv4j.util.ConvertUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * Created by coolkid on 2021/12/24.
 */

@Slf4j
public class CommandUtil {
    @SneakyThrows
    public static void main(String[] args) {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("h", "help", false, "显示帮助");
        options.addOption("f", "file", true, "输入文件夹 如果不填表示jar运行得文件夹");
        options.addOption("o", "out", true, "输出文件夹 如果不填表示覆盖源文件");
        options.addOption("c", "code", true, "指定输出文件字符串编码 \n 支持:UTF-8,UTF-16LE,UTF-16BE,UTF-32LE,UTF-32BE,GB18030,Shift_JIS,Big5");
        options.addOption("fi", "filter", true, "文件类型 支持多个用英文逗号(,) 分割例如:sql,txt \n 默认:sql,txt,ass,srt,ssa");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);


        String input;
        String output;
        String code;
        String filter;

        if(cmd.hasOption("h")){
            formatter.printHelp("java -jar icon2j -f path -c UTF-8", options);
            System.exit(0);
        }

        if(cmd.hasOption("f")) {
            input = cmd.getOptionValue("f");
        }else{
            input = System.getProperty("user.dir");
        }
        log.info("输入文件夹:{}", input);

        if(cmd.hasOption("o")) {
            output = cmd.getOptionValue("o");
            log.info("输出文件夹:{}", output);
        }else{
            output = null;
            log.info("无输出文件夹 将替换源文件");
        }

        if(cmd.hasOption("c")) {
            code = cmd.getOptionValue("c");
            log.info("输出文件字符串编码:{}", code);
        }else{
            log.error("无输出文件字符串编码, 默认使用UTF-8");
            code = "UTF-8";
        }

        if(cmd.hasOption("fi")) {
            filter = cmd.getOptionValue("fi");
        }else{
            filter = "sql,txt,ass,srt,ssa";
        }
        log.info("文件类型:{}", filter);

        AppRun appRun = new AppRun();
        appRun.setInput(input);
        appRun.setOutput(output);
        appRun.setCode(code);
        appRun.setConvertUtil(new ConvertUtil());
        appRun.setFilter(filter);

        appRun.run();
    }
}
