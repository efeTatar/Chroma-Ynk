# Chromat-Ynk


Implementation of the Chromat-Ynk project by Efe Tatar, Nathan Signoud, Alize Fortunel, Francois Bretagne.
The concept behind the project belongs to Cy Tech who charged the authors with its implementation.

This project is composed of two modules, the language interpreter and the interface.

## Interpreter
The Abbas language interpreter takes in a .abbas file and interprets it. It implements variables, functions, arithmetic operations
and boolean operations. Although the language is standalone, it has additionnal features when linked to an interface. Theses features
include managing cursors enabling the user to draw lines on a canvas. Because of the nature of the interpreter, the user can manage
to draw smooth shapes such circles, squares, sinusoidal curves and much more.

### A simple guide to the abbas language
All files must end with the .abbas extention.<br><br>

Declaring variables:<br>
There are 3 types available: NUM, STR, BOOL<br>
{type} {name} [ = {value} ] ;<br><br>

Declaring loops:<br>
There are 3 loops available: IF, WHILE, FOR<br>
{type} condition { body }<br><br>

Declaring functions:<br>
DEF {name} { body }<br><br>

Calling functions:<br>
functions do not pocess strict parameters but take arguments instead.
Let exp be a function, in order to call exp, one must:<br>
exp(operation, operation, operation, ...);<br>
There parameters will be available to the user as arg0, arg1, ...<br>
The variable argc is automatically created and stores the number of arguments<br><br>

Return values<br>
Function can return values at any given moment with the keyword RETURN<br>
the implicit main function can infact return a value.<br><br>

How to draw ?<br>
The user has access to the following instructions:<br>
FWD value [%]<br>
BWD value [%]<br>
TURN value<br>
MOV x [%], y [%]<br>
POS x [%], y [%]<br>
HIDE<br>
SHOW<br>
PRESS value [%]<br>
COLOR #RRGGBB<br>
COLOR red, green,blue<br>
THICK value<br>
LOOKAT cursorID<br>
LOOKAT x [%], y [%]<br>
CURSOR cursorID<br>
SELECT cursorID<br>
Note that these instruction must be called as if they were functions.<br><br>

## Interface
The Interface enables the user to interpret .abbas files and visualise outputs. The user can choose to visualise the drawing process
either step by step or in a constant speed.


## Acknowledgement
We thank our professors charged with the supervision of this project who helped us very much during the development process.
