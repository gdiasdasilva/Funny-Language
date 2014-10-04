import ast.ASTCond;
import ast.ASTEq;
import ast.ASTGr;
import ast.ASTNode;
import ast.ASTNum;
import parser.ParseException;
import parser.Parser;
import semantics.IValue;
import semantics.UnparseVisitor;
import semantics.compiler.CompilerVisitor;
import semantics.interpreter.EvalVisitor;

public class Main {

	public static void main(String args[]) throws ParseException {
		Parser parser = new Parser(System.in);
		System.out.println("Welcome.");
		
//		ASTNode exp = new ASTCond(
//		new ASTEq(new ASTPlus(new ASTNum(IValue.fromInteger(3)), new ASTNum(IValue.fromInteger(3))), new ASTDiv(new ASTNum(IValue.fromInteger(12)), new ASTPlus(new ASTNum(IValue.fromInteger(-1)), new ASTNum(IValue.fromInteger(3))))),
//		new ASTPlus(new ASTNum(IValue.fromInteger(1)), new ASTNum(IValue.fromInteger(2))),
//		new ASTMul(new ASTNum(IValue.fromInteger(2)), new ASTNum(IValue.fromInteger(7)))
//		);
		
		ASTNode exp = new ASTCond(
				new ASTEq(new ASTNum(IValue.fromInteger(99)), new ASTNum(IValue.fromInteger(100))),
				new ASTCond
				(
						new ASTGr(new ASTNum(IValue.fromInteger(20)), new ASTNum(IValue.fromInteger(15))),
						new ASTNum(IValue.fromInteger(22)),
						new ASTNum(IValue.fromInteger(92))
				),
				new ASTNum(IValue.fromInteger(-1))
		);
		
//		ASTNode exp = new ASTCond(
//				new ASTEq(new ASTNum(IValue.fromInteger(99)), new ASTNum(IValue.fromInteger(100))),
//				new ASTNum(IValue.fromInteger(22)),
//				new ASTNum(IValue.fromInteger(-1))
//		);
		
//		ASTNode exp = new ASTCond(
//				new ASTEq(new ASTNum(IValue.fromInteger(99)), new ASTNum(IValue.fromInteger(100))),
//				new ASTCond
//				(
//						new ASTGr(new ASTNum(IValue.fromInteger(20)), new ASTNum(IValue.fromInteger(15))),
//						new ASTNum(IValue.fromInteger(22)),
//						new ASTNum(IValue.fromInteger(92))
//				),
//				new ASTCond
//				(
//						new ASTEq(new ASTNum(IValue.fromInteger(20)), new ASTNum(IValue.fromInteger(20))),
//						new ASTNum(IValue.fromInteger(5)),
//						new ASTNum(IValue.fromInteger(4))
//				)
//		);
		
		System.out.println("Ok:  "+exp.accept(new UnparseVisitor()));
		System.out.println("Val: "+exp.accept(new EvalVisitor()));
		System.out.println("Code:\n"+exp.accept(new CompilerVisitor()));
//		while (true) {
//			try {
//				ASTNode exp = parser.start();
//				System.out.println("Ok:  "+exp.accept(new UnparseVisitor()));
//				System.out.println("Val: "+exp.accept(new EvalVisitor()));
//				System.out.println("Code:\n"+exp.accept(new CompilerVisitor()));
//			} catch (Error e) {
//				System.out.println("Parsing error");
//				System.out.println(e.getMessage());
//				break;
//			} catch (Exception e) {
//				System.out.println("NOK.");
//				e.printStackTrace();
//				break;
//			}
//		}
	}
}