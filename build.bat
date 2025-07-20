@echo off
set SRC=src\main\java
set OUT=out
set JAR=dist\password-generator.jar
set MAIN_CLASS=gr.marou.password_generator.Main

if not exist %OUT% mkdir %OUT%
if not exist dist mkdir dist

:: Compile all Java source files
dir /S /B %SRC%\*.java > sources.txt
javac -d %OUT% @sources.txt

:: Package into runnable JAR
jar cfe %JAR% %MAIN_CLASS% -C %OUT% .

echo JAR created at %JAR%