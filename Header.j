.class public Code
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 1
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.ou4
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here

       ; START