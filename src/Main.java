import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import parser.ParseException;
import parser.Parser;
import semantics.IdentiferDeclaredTwiceException;
import semantics.TypeErrorException;
import semantics.UndefinedIdException;
import semantics.interpreter.EvalVisitor;
import ast.ASTNode;

public class Main {
	
	@SuppressWarnings("static-access")
	public static void main(String args[]) throws Exception {
		
//		CodeBlock cb;
		
		boolean interactive = false;
		InputStream stream = null;
		
		if (args.length == 0) { // interactive mode
			System.out.println("Welcome to the interactive mode.");
			System.out.println("You can start typing expressions and their result will be printed in this terminal.");
			System.out.println("'print(ln)' expressions will also print their arguments as side-effect");
			System.out.println("To read the program from 'filename' run 'java Main filename' from your shell");
			System.out.println();
			interactive = true;
//			parser = new Parser(System.in);
			stream = System.in;
		} else if (args.length == 1) { // read from file named like the argument
			try {
//				parser = new Parser(new FileInputStream(args[0]));
				stream = new FileInputStream(args[0]);
			} catch (FileNotFoundException e) {
				System.err.println("The file you specified does not exist.");
				System.exit(-1);
			}
		} else { // print usage
			System.out.println("Usage: java Main [filename]");
			System.out.println("No filename means entering the interactive mode");
			System.exit(0);
		}
		
		Parser parser = new Parser(stream);
		
		while (true)
		{
			try
			{
				ASTNode exp = parser.start();
				if (interactive) {
//					System.out.println("Ok: " + exp.accept(new UnparseVisitor())); // for debug purposes
					System.out.println("Val: " + exp.accept(new EvalVisitor()));
				}
				else
				{
					exp.accept(new EvalVisitor());
				}
				
				//cb = exp.accept(new CompilerVisitor());
				//cb.writeToFile(new File("Code.j"));
				//System.out.println("Code written to file \"Code.j\" in the project or bin directory.");
			}
//			catch (Error e)
//			{
//				System.err.println("Parsing error");
//				System.err.println(e.getMessage());
////				e.printStackTrace();
////				break;
//				parser.ReInit(stream);
//			}
			catch(UndefinedIdException e)
			{
				System.err.println(e.getMessage());
			}
			catch (IdentiferDeclaredTwiceException e)
			{
				System.err.println(e.getMessage());
			}
			catch (TypeErrorException e) {
				System.err.println(e.getMessage());
			}
			catch (ParseException e)
			{
				System.err.println(e.getMessage());
				parser.ReInit(stream);
			}
		}
	}
}