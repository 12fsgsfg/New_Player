@echo off
echo 正在編譯新玩家歡迎插件...
echo.

REM 檢查 Java 是否安裝
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo 錯誤：未找到 Java，請先安裝 Java
    pause
    exit /b 1
)

echo Java 版本：
java -version
echo.

echo 清理舊的編譯文件...
call mvnw.cmd clean

echo 編譯插件...
call mvnw.cmd package

if %errorlevel% equ 0 (
    echo.
    echo 編譯成功！
    echo 插件 JAR 文件位於：target\new-player-welcome-1.0.0.jar
    echo.
    echo 請將此 JAR 文件複製到您的 Minecraft 服務器的 plugins 資料夾中
) else (
    echo.
    echo 編譯失敗，請檢查錯誤信息
)

pause
