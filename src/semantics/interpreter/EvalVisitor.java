package semantics.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import semantics.Environment;
import semantics.SemanticException;
import semantics.UndefinedIdException;
import semantics.Visitor;
import semantics.values.*;
import ast.*;

public class EvalVisitor implements Visitor<IValue, IValue> {
	
	private final CmdValue cmdValue;
	
	public EvalVisitor() {
		cmdValue = new CmdValue();
	}
	
	@Override
	public IValue visit(ASTNum num, Environment<IValue> e) {
		return new IntValue(num.integer);
	}
	
	@Override
	public IValue visit(ASTBool bool, Environment<IValue> e) {
		return new BoolValue(bool.bool);
	}
	
	@Override
	public IValue visit(ASTString string, Environment<IValue> e) {
		return new StringValue(string.string);
	}

	@Override
	public IValue visit(ASTPlus plus, Environment<IValue> e) throws SemanticException {
		return new IntValue(((IntValue) plus.l.accept(this, e)).val + ((IntValue) plus.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTSub sub, Environment<IValue> e) throws SemanticException {
		return new IntValue(((IntValue) sub.l.accept(this, e)).val - ((IntValue) sub.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTMul mul, Environment<IValue> e) throws SemanticException {
		return new IntValue(((IntValue) mul.l.accept(this, e)).val * ((IntValue) mul.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTDiv div, Environment<IValue> e) throws SemanticException {
		return new IntValue(((IntValue) div.l.accept(this, e)).val / ((IntValue) div.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTUnMinus um, Environment<IValue> e) throws SemanticException {
		return new IntValue(-((IntValue) um.v.accept(this, e)).val);
	}
	
	@Override
	public IValue visit(ASTEq eq, Environment<IValue> e) throws SemanticException {
		IValue lval = eq.l.accept(this, e);
		IValue rval = eq.r.accept(this, e);
		switch (lval.getType()) {
		case INTEGER:
			return new BoolValue(((IntValue) lval).val == ((IntValue) rval).val);
		case BOOLEAN:
			return new BoolValue(((BoolValue) lval).b == ((BoolValue) rval).b);
		default:
			return new BoolValue(((StringValue) lval).s.equals(((StringValue) rval).s));
		}
	}

	@Override
	public IValue visit(ASTAnd and, Environment<IValue> e) throws SemanticException{
		return new BoolValue(((BoolValue) and.l.accept(this, e)).b && ((BoolValue) and.r.accept(this, e)).b);
	}

	@Override
	public IValue visit(ASTOr or, Environment<IValue> e) throws SemanticException {
		return new BoolValue(((BoolValue) or.l.accept(this, e)).b || ((BoolValue) or.r.accept(this, e)).b);
	}

	@Override
	public IValue visit(ASTNeq neq, Environment<IValue> e) throws SemanticException {
		IValue lval = neq.l.accept(this, e);
		IValue rval = neq.r.accept(this, e);
		switch (lval.getType()) {
		case INTEGER:
			return new BoolValue(((IntValue) lval).val != ((IntValue) rval).val);
		case BOOLEAN:
			return new BoolValue(((BoolValue) lval).b != ((BoolValue) rval).b);
		default:
			return new BoolValue(!((StringValue) lval).s.equals(((StringValue) rval).s));
		}
	}

	@Override
	public IValue visit(ASTLseq lseq, Environment<IValue> e) throws SemanticException {
		return new BoolValue(((IntValue) lseq.l.accept(this, e)).val <= ((IntValue) lseq.r.accept(this, e)).val);		
	}

	@Override
	public IValue visit(ASTGreq greq, Environment<IValue> e) throws SemanticException{
		return new BoolValue(((IntValue) greq.l.accept(this, e)).val >= ((IntValue) greq.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTLs ls, Environment<IValue> e) throws SemanticException {
		return new BoolValue(((IntValue) ls.l.accept(this, e)).val < ((IntValue) ls.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTGr gr, Environment<IValue> e) throws SemanticException{
		return new BoolValue(((IntValue) gr.l.accept(this, e)).val > ((IntValue) gr.r.accept(this, e)).val);
	}

	@Override
	public IValue visit(ASTCond cond, Environment<IValue> e) throws SemanticException
	{
		return (((BoolValue) cond.condNode.accept(this, e)).b ? cond.thenNode.accept(this, e) : cond.elseNode.accept(this, e));
	}

	@Override
	public IValue visit(ASTNot n, Environment<IValue> e) throws SemanticException {
		return new BoolValue(!((BoolValue) n.v.accept(this, e)).b);
	}
	
	@Override
	public IValue visit(ASTId id, Environment<IValue> e) throws SemanticException {
		if (e != null)
			return e.find(id.id);
		throw new UndefinedIdException("Undefined id " + id);
	}

	@Override
	public IValue visit(ASTDecl decl, Environment<IValue> e) throws SemanticException {
		e = e.beginScope();
		Iterator<String> idit = decl.ids.iterator();
		Iterator<ASTNode> defit = decl.defs.iterator();
		while (idit.hasNext())
			e.assoc(idit.next(), defit.next().accept(this, e));
		IValue v = decl.body.accept(this, e);
		e.endScope();
		return v;
	}

	@Override
	public IValue visit(ASTAssign astAssign, Environment<IValue> e) throws SemanticException {
		return ((RefValue) astAssign.l.accept(this, e)).setVal(astAssign.r.accept(this, e));
	}

	@Override
	public IValue visit(ASTDeref astDeref, Environment<IValue> e) throws SemanticException {
		return ((RefValue) astDeref.node.accept(this, e)).getVal();
	}
	
	@Override
	public IValue visit(ASTWhile astWhile, Environment<IValue> e) throws SemanticException {
		while (((BoolValue) astWhile.c.accept(this, e)).b)
			astWhile.b.accept(this, e);
		return cmdValue;
	}
	
	@Override
	public IValue visit(ASTNew astNew, Environment<IValue> e) throws SemanticException {
		return new RefValue(astNew.node.accept(this, e));
	}
	
	@Override
	public IValue visit(ASTPrint astPrint, Environment<IValue> e) throws SemanticException {
		System.out.print(astPrint.node.accept(this, e));
		return cmdValue;
	}

	@Override
	public IValue visit(ASTPrintln astPrintln, Environment<IValue> e) throws SemanticException {
		System.out.println(astPrintln.node.accept(this, e));
		return cmdValue;
	}

	@Override
	public IValue visit(ASTSeq astSeq, Environment<IValue> e) throws SemanticException {
		astSeq.f.accept(this, e);
		return astSeq.s.accept(this, e);
	}

	@Override
	public IValue visit(ASTCall astCall, Environment<IValue> e) throws SemanticException {
		FunValue closure = (FunValue) astCall.fun.accept(this, e);
		List<IValue> vargs = new ArrayList<IValue>();
		
		for (ASTNode arg : astCall.args)
			vargs.add(arg.accept(this, e));
		
		Environment<IValue> e1 = closure.beginScope();
			
		Iterator<String> pit = closure.parameters.iterator();
		Iterator<IValue> vit = vargs.iterator();
		
		while (pit.hasNext())
			while (vit.hasNext())
				e1.assoc(pit.next(), vit.next());
		
		IValue result = closure.expBody.accept(this, e1);
		e1.endScope();
		return result;
	}

	@Override
	public IValue visit(ASTFun astFun, Environment<IValue> e) throws SemanticException {
		return new FunValue(astFun.body, astFun.paramNames, e);
	}

	@Override
	public IValue visit(ASTIf astIf, Environment<IValue> e) throws SemanticException {
		if (((BoolValue) astIf.condNode.accept(this, e)).b)
			return astIf.thenNode.accept(this, e);
		if (astIf.elseNode != null)
			return astIf.elseNode.accept(this, e);
		return cmdValue;
	}

	@Override
	public IValue visit(ASTField astField, Environment<IValue> e)
			throws SemanticException {
		return ((RecValue) astField.record.accept(this, e)).getValueForField(astField.fieldId);
	}

	@Override
	public IValue visit(ASTRecord astRecord, Environment<IValue> e)
			throws SemanticException {
		Map<String, IValue> r = new HashMap<String, IValue>();
		Iterator<ASTNode> fit = astRecord.getFieldsIterator();
		Iterator<String> fnit = astRecord.getFieldNamesIterator();
		while (fit.hasNext())
			r.put(fnit.next(), fit.next().accept(this, e));
		return new RecValue(r);
	}
}
