import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import parser.ParseException;
import parser.Parser;
import semantics.UndefinedIdException;
import semantics.compiler.CodeBlock;
import semantics.compiler.CompilerVisitor;
import semantics.interpreter.EvalVisitor;
import ast.ASTNode;
import semantics.*;

public class Main {

	public static void main(String args[]) throws ParseException {
		Parser parser = null;
		final IEnv env = new Env();
		try
		{
			parser = new Parser(new FileInputStream(args[0]));
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		System.out.println("Welcome.");
		
		CodeBlock cb;
		
		while (true)
		{
			try
			{
				ASTNode exp = parser.start();
				System.out.println("Val: " + exp.accept(new EvalVisitor(env)));
				
				//cb = exp.accept(new CompilerVisitor());
				//cb.writeToFile(new File("Code.j"));
				//System.out.println("Code written to file \"Code.j\" in the project or bin directory.");
			}
			catch (Error e)
			{
				System.out.println("Parsing error");
				System.out.println(e.getMessage());
				break;
			} catch(UndefinedIdException e) {
				System.out.println("The id '" + e.invalidId + "' is not defined in this scope.");
			} catch (Exception e) {
				System.out.println("NOK.");
				e.printStackTrace();
				break;
			}
		}
	}
}