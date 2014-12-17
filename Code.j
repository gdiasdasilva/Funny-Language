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
sipush 2342
sipush 3364
if_icmplt Then2
sipush 0
goto Continue2
Then2:
sipush 1
Continue2:
sipush 0
iand
sipush 0
if_icmpne Then1
sipush 200
goto Continue1
Then1:
sipush 100
Continue1:
       ; END


       ; convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println 
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return

.end method
