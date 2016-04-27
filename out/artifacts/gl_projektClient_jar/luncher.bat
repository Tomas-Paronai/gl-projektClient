@echo off
cd "C:\Users\tomas\Documents\NetBeansProjects\gl-projektClient\out\artifacts\gl_projektClient_jar"
set /p employee=<employee.txt
java -jar gl-projektClient.jar %employee%
exit