#!/bin/sh

set -e

version=v24_9
jar_name=aspose-words-24.9-jdk17

echo '==> 官网下载'
#wget https://repository.aspose.com/repo/com/aspose/aspose-words/24.9/${jar_name}.jar
cp ${jar_name}.jar.bak ${jar_name}.jar

echo '==> 用7z命令删除jar内的META-INF文件夹'
7z d ${jar_name}.jar META-INF

cd ../../
echo '==> 执行破解,生成类'
javac -classpath .:../../../javassist-3.24.1-GA.jar ./word/${version}/AsposeWords.java
java -classpath .:../../../javassist-3.24.1-GA.jar word.${version}.AsposeWords
cd ./word/${version}/

echo '==> 用jar命令更新jar内的类'
for file in "com/aspose/words"/*; do
    if [ -f "$file" ]; then
        echo "替换前 $file"
        jar -tvf ${jar_name}.jar | grep $file
        jar uvf ${jar_name}.jar ${file}
        echo "替换后 $file"
        jar -tvf ${jar_name}.jar | grep $file
    fi
done
rm -rf com/

echo '==> 重命名'
rm -f ${jar_name}-cracked.jar
mv ${jar_name}.jar ${jar_name}-cracked.jar

cd ../../
echo '==> 测试转换'
javac -classpath ./word/${version}/${jar_name}-cracked.jar ./word/Office2PdfTest.java
java -classpath .:./word/${version}/${jar_name}-cracked.jar word.Office2PdfTest "../../../file/"
