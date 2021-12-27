# iconv4j是一个java版本的文本文件编码转换工具,支持自动识别文件编码

```
使用方法:
java -jar icon4j.jar -f path -c UTF-8
-c,--code <arg>      指定输出文件字符串编码
                     支持:UTF-8,UTF-16LE,UTF-16BE,UTF-32LE,UTF-32BE,GBK
,Shift_JIS,Big5
-f,--file <arg>      输入文件夹 如果不填表示jar运行得文件夹
-fi,--filter <arg>   文件类型 支持多个用英文逗号(,) 分割例如:sql,txt
                     默认:sql,txt,ass,srt,ssa
-h,--help            显示帮助
-o,--out <arg>       输出文件夹 如果不填表示覆盖源文件
```



使用以下开源项目

1. [icu4j](https://github.com/unicode-org/icu)
2. [commons.apache.org](https://commons.apache.org/)
3. [log4j](https://logging.apache.org/log4j)
4. [slf4j](https://www.slf4j.org/)
5. [lombok](https://projectlombok.org/)

