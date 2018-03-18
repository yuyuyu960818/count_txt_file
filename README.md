# count_txt_file
一个java小程序，能统计.c文件的行数、单词数、字符数等信息
文件中包含了：

bin文件夹：包含了wc.exe、依赖的jre、测试用的result.txt、output.txt、file.c等文件。
jre文件夹：jre包
src文件夹：保存了源码main.java和测试用的代码testmain.java
根目录下还有一些在编译时使用的测试文件result.txt等

小程序实现了：
  基本功能
-c 输出文件的字符数
-w 输出文件单词总数
-l 输出文件行数
-o 指定输出文件名
  进阶功能
-s 处理递归处理目录下符合条件的文件
-a 输出更复杂的数据（代码行 / 空行 / 注释行）
-e 使用停用词表，统计文件单词总数时，不统计该表中的单词

输入规范：
wc.exe / 传参数 -c、-s、-l、-a、-w，顺序无关 / 输入指定的file.c或者*.c / 输入-e 和指定的停用词表文件 /输入-o和指定输出文件
例如：
wc.exe -c file.c  
wc.exe -w file.c  
wc.exe -l file.c   
wc.exe -o outputFile.txt  
wc.exe -s -a –c -w *.c–e stop.txt –o –output.txt
wc.exe -s -l -a -w -c *.c -e stop.txt -o output.txt
等等
