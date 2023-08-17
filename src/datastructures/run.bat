@echo off

rem Set the paths to your JARs (old version and new version)
set "OLD_JAR_PATH=C:\path\to\old\calendar.jar"
set "NEW_JAR_PATH=C:\path\to\new\calendar.jar"

rem Set the classpath to include the appropriate JAR (new version)
set "CLASSPATH=%NEW_JAR_PATH%;C:\path\to\your\application.jar"

rem Set the main class name
set "MAIN_CLASS=com.your.package.YourMainClass"

rem Set the arguments for your main method
set "LIBRARY_NAME=yourLibraryName"
set "API_NAME=yourApiName"
set "CONFIG_ARG_1=config1"
set "CONFIG_ARG_2=config2"
set "CONFIG_ARG_3=config3"
set "CONFIG_ARG_4=config4"
set "CONFIG_ARG_5=config5"
set "OTHER_ARGS=otherArgs"

rem Construct the command to execute the Java program
set "COMMAND=java -cp %CLASSPATH% %MAIN_CLASS% %LIBRARY_NAME% %API_NAME% %CONFIG_ARG_1% %CONFIG_ARG_2% %CONFIG_ARG_3% %CONFIG_ARG_4% %CONFIG_ARG_5% %OTHER_ARGS%"

rem Execute the command
%COMMAND%