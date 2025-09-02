@echo off
echo Cleaning IntelliJ IDEA and Gradle files...

REM 删除 .idea 文件夹
if exist .idea (
    rmdir /s /q .idea
    echo Deleted .idea
)

REM 删除所有 *.iml 文件
del /s /q *.iml 2>nul
echo Deleted *.iml files

REM 删除 Gradle 缓存文件夹
if exist .gradle (
    rmdir /s /q .gradle
    echo Deleted .gradle
)

echo Done. Now re-open the project with pom.xml as Maven project.
pause
