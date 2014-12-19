import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import parser.ParseException;
import parser.Parser;
import semantics.EnvironmentImpl;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;
import semantics.compiler.CodeBlock;
import semantics.compiler.CompilerEnvironment;
import semantics.compiler.CompilerVisitor;
import semantics.interpreter.EvalVisitor;
import semantics.typeSystem.Type;
import semantics.typeSystem.TypeErrorException;
import semantics.typeSystem.TypecheckVisitor;
import semantics.values.IValue;
import ast.ASTNode;

public class Main {
	
	@SuppressWarnings("static-access")
	public static void main(String args[]) throws Exception {
				
		boolean interactive = false;
		boolean compiler = false;
		InputStream inputStream = null;
		
		if (args.length == 0) { // interactive mode
			System.out.println("Tnteractive mode.");
			System.out.println("To read the program from 'filename' run 'java Main filename' from your shell");
			System.out.println();
			interactive = true;
			inputStream = System.in;
		} else if (args.length == 1) { // read from file named the same as the argument
			try {
				inputStream = new FileInputStream(args[0]);
			} catch (FileNotFoundException e) {
				System.err.println("The file you specified does not exist.");
				System.exit(-1);
			}
		} else if (args.length == 2) {
			if (args[0].equals("--compiler")) {
				try {
					inputStream = new FileInputStream(args[0]);
				} catch (FileNotFoundException e) {
					System.err.println("The file you specified does not exist.");
					System.exit(-1);
				}
				compiler = true;
			}
		} else { // print usage
			System.out.println("Usage: java Main [filename]");
			System.out.println("No filename means entering the interactive mode");
			System.exit(0);
		}
		
		Parser parser = new Parser(inputStream);
		
		while (true) {
			try {
				ASTNode exp = parser.Prog();
				if (interactive) {
					System.out.println("Expression type: " + exp.accept(new TypecheckVisitor(), new EnvironmentImpl<Type>()));
					System.out.println("Val: " + exp.accept(new EvalVisitor(), new EnvironmentImpl<IValue>()));
				}
				else if (compiler) {
					exp.accept(new EvalVisitor(), new EnvironmentImpl<IValue>());
				} else { // compiler
					exp.accept(new TypecheckVisitor(), new EnvironmentImpl<Type>());
					CodeBlock cb = exp.accept(new CompilerVisitor(), new CompilerEnvironment()); // requires previous TypeChecking to tag the tree
					cb.writeToFile();
					System.out.println("Code written to file \"" + CodeBlock.MAIN_CLASS_NAME + ".j\" in the project or bin directory.");
				}
			}
			catch(UndefinedIdException e) {
				System.err.println(e.getMessage());
			} catch (IdentiferDeclaredTwiceException e) {
				System.err.println(e.getMessage());
			} catch (TypeErrorException e) {
				System.err.println(e.getMessage());
			} catch (ParseException e) {
				System.err.println(e.getMessage());
				parser.ReInit(inputStream);
			}
		}
	}
}