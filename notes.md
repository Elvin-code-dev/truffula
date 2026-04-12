# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
-This is like the main starting point of the program
-It receives command lines arguments can think of it as command center 
-From what i understood it will create a truffula options object using arguments
-Then it will create a truffula printer object 
-The last thing it deos is calls print tree to display the directory structure

## ConsoleColor.java
-This is like an enum that stores diffrent kinds of colors 
-Each color has like a escape way/code
-The codes are used to change text color in the console
-There is a reset color to get back the defaults
-get code returns the ASNI code
-there is a toString that also returns the code so it can be used directly in the print statements

## ColorPrinter.java / ColorPrinterTest.java
-This class is used to print colored text to a PrintStream which is like a System.out
-It keeps track of the current color
-The set current color changes the color
-If reset is true, it resets color after printing
-println adds a newline after printing
-The test file checks if the correct colored output is printed
-It uses ByteArrayOutputStream to get or see the output instead of printing it to the console
-Then it compares expected output with actual output

## TruffulaOptions.java / TruffulaOptionsTest.java
-This class bascially just reads command line arguments
-it looks for which directory to use 
-it also checks if hidden files should be shown -h
-if color should be used -nc disables the color
-The last argument is always the directory path
-from what i can see it uses java.io.File to check if the path exists and is a directory
-the test checks if the correct root directory is set and if flags like -h and -nc work correctly

## TruffulaPrinter.java / TruffulaPrinterTest.java
-This class prints the directory tree
-It uses TruffulaOptions to know how to print
-It will later go through folders using recursion
-print with indentation
-handle hidden files
-use colors
-sort files alphabetically
-It uses ColorPrinter instead of System.out
-The test builds a fake directory structure
-Then it checks if the output matches exactly

## AlphabeticalFileSorter.java
-This class bascialy just sorts files alphabetically
-It ignores uppercase/lowercase differences
-Uses Arrays.sort with compareToIgnoreCase
-It will be used by TruffulaPrinter later