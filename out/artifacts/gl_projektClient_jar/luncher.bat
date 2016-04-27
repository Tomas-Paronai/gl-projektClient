@echo off
cd "C:\Users\tomas\Documents\NetBeansProjects\gl-projektClient\out\artifacts\gl_projektClient_jar"
set /p employee=<card/employee.txt
echo %employee%
pause
java -jar reader/gl-projektClient.jar %employee%
pause
exit