@echo off
cd "D:\Projects\School\gl-projektClient\out\artifacts\gl_projektClient_jar"
set /p employee=<employee.txt
java -jar gl-projektClient.jar %employee%
pause
exit