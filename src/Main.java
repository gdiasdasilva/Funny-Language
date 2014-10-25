import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import semantics.UndefinedIdException;
import semantics.UnparseVisitor;
import semantics.interpreter.EvalVisitor;
import ast.ASTAssign;
import ast.ASTDecl;
import ast.ASTDeref;
import ast.ASTId;
import ast.ASTLs;
import ast.ASTNew;
import ast.ASTNode;
import ast.ASTNum;
import ast.ASTPlus;
import ast.ASTWhile;

public class Main {

//	public static void main(String args[]) throws ParseException {
	public static void main(String args[]) throws Exception {
		Parser parser = null;
//		final IEnv env = new Env();
		
//		try
//		{
//			parser = new Parser(new FileInputStream(args[0]));
//		}
//		catch (FileNotFoundException e)
//		{
//			System.out.println("Ficheiro de input não encontrado em args[0].");
//		}
		
//		System.out.println("Welcome.");
		
//		CodeBlock cb;
		
		boolean interactive = false;
		
		if (args.length == 0) { // interactive mode
			System.out.println("Welcome to interactive mode.");
			System.out.println("You can start typing expressions and their result will be printed in this terminal.");
			System.out.println("'print(ln)' expressions will also print their arguments as side-effect");
			System.out.println("To read the program from 'filename' run 'java Main filename' from your shell");
			System.out.println();
			interactive = true;
			parser = new Parser(System.in);			
		} else if (args.length == 1) { // read from file named like the argument
			try {
				parser = new Parser(new FileInputStream(args[0]));
			} catch (FileNotFoundException e) {
				System.err.println("The file you specified does not exist.");
				System.exit(-1);
			}
		} else { // print usage
			System.out.println("Usage: java Main [filename]");
			System.out.println("No filename means entering interactive mode");
			System.exit(0);
		}
		
//		ASTNode exp = new ASTEq( new ASTString("uma_string"), new ASTString("uma_string"));
//		List<String> ids = new ArrayList<String>();
//		List<ASTNode> defs = new ArrayList<ASTNode>();
//		ids.add("x");
//		defs.add(new ASTNew(new ASTNum(1)));
//		ASTNode cond = new ASTLs(
//				new ASTDeref(new ASTId("i"))
//				, new ASTNum(10));
//		ASTNode body = new ASTAssign( new ASTId("i"), new ASTPlus( new ASTDeref( new ASTId("i")), new ASTNum(1) ) );
//		ASTNode loop = new ASTWhile(cond, body);
//		ASTNode exp = new ASTDecl(ids, defs, loop);
//		System.out.println("Ok: " + exp.accept(new UnparseVisitor()));
//		System.out.println("Val: " + exp.accept(new EvalVisitor(null)));
		
//		ASTNode exp0 = new ASTDecl(ids, defs, new ASTDeref(new ASTId("x")));
		
//		System.out.println("Ok: " + exp0.accept(new UnparseVisitor()));
//		System.out.println("Val: " + exp0.accept(new EvalVisitor(null)));
		
		while (true)
		{
			try
			{
				ASTNode exp = parser.start();
				if (interactive) {
					System.out.println("Ok: " + exp.accept(new UnparseVisitor()));
					System.out.println("Val: " + exp.accept(new EvalVisitor(null)));
				}
				else{
					System.out.println("Ok: " + exp.accept(new UnparseVisitor()));
					System.out.println("Val: " + exp.accept(new EvalVisitor(null)));
				}
				//cb = exp.accept(new CompilerVisitor());
				//cb.writeToFile(new File("Code.j"));
				//System.out.println("Code written to file \"Code.j\" in the project or bin directory.");
			}
			catch (Error e)
			{
				System.out.println("Parsing error");
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}
			catch(UndefinedIdException e)
			{
//				System.out.println("The id '" + e.invalidId + "' is not defined in this scope.");
				System.err.println("UndefinedId");
//				System.err.println(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("NOK.");
				e.printStackTrace();
				break;
			}
		}
	}
}