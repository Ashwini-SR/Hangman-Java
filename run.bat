@echo off
echo =============================================
echo   Welcome to Hangman Game!
echo =============================================
echo.
echo Compiling...
if not exist out mkdir out
javac -d out com\btech\hangman\*.java
IF %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed. Make sure Java 17+ is installed.
    pause
    exit /b 1
)
echo Done! Starting game...
echo.
java -cp out com.btech.hangman.MainApp
pause
