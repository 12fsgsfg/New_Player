#!/bin/bash

echo "正在編譯新玩家歡迎插件..."
echo

# 檢查 Maven 是否安裝
if ! command -v mvn &> /dev/null; then
    echo "錯誤：未找到 Maven，請先安裝 Maven"
    echo "下載地址：https://maven.apache.org/download.cgi"
    exit 1
fi

echo "清理舊的編譯文件..."
mvn clean

echo "編譯插件..."
mvn package

if [ $? -eq 0 ]; then
    echo
    echo "編譯成功！"
    echo "插件 JAR 文件位於：target/new-player-welcome-1.0.0.jar"
    echo
    echo "請將此 JAR 文件複製到您的 Minecraft 服務器的 plugins 資料夾中"
else
    echo
    echo "編譯失敗，請檢查錯誤信息"
fi
