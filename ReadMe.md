

#IC Project

##General Info

Project for Interpretation and Compilation course @ FCT-UNL, 14/15.

#####Authors

* Gonçalo Dias da Silva
* Rui Carvalho

##Usage

#####Interpreter

* Normal mode: `./funny file`
* Interactive mode: `./funny`

#####Compiler
    
`./funnyc file`

This instruction requires jasmin’s executable file to be in PATH. It will create a new directory named *code* in the current directory, with the `*.j` and `*.class` files, in which you will need to run the file `Code.class`.

##Implementation

* Interpreter and typechecker for the full Funny language.
* Compiler for the Funny language without functions.