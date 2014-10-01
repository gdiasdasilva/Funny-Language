import ast.ASTNode;
import parser.ParseException;
import parser.Parser;
import semantics.EvalVisitor;
import semantics.UnparseVisitor;

public class Main {

	public static void main(String args[]) throws ParseException {
		Parser parser = new Parser(System.in);
		System.out.println("Welcome.");
//		ASTNode exp = new ASTCond(
//		new ASTEq(new ASTPlus(new ASTNum(IValue.fromInteger(3)), new ASTNum(IValue.fromInteger(3))), new ASTDiv(new ASTNum(IValue.fromInteger(12)), new ASTPlus(new ASTNum(IValue.fromInteger(-1)), new ASTNum(IValue.fromInteger(3))))),
//		new ASTPlus(new ASTNum(IValue.fromInteger(1)), new ASTNum(IValue.fromInteger(2))),
//		new ASTMul(new ASTNum(IValue.fromInteger(2)), new ASTNum(IValue.fromInteger(7)))
//		);
		while (true) {
			try {
				ASTNode exp = parser.start();
				System.out.println("Ok:  "+exp.accept(new UnparseVisitor()));
				System.out.println("Val: "+exp.accept(new EvalVisitor()));
			} catch (Error e) {
				System.out.println("Parsing error");
				System.out.println(e.getMessage());
				break;
			} catch (Exception e) {
				System.out.println("NOK.");
				e.printStackTrace();
				break;
			}
		}
	}
}