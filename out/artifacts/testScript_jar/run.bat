@echo off
:input
set /p id="Enter userID "
if defined id ( 
	call test2.bat %id% 
) else (
	echo No id read!
	goto input
)
:done
pause