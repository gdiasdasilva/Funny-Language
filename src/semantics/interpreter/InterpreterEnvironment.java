package semantics.interpreter;

import java.util.HashMap;
import java.util.Map;

import semantics.Environment;
import semantics.IValue;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class InterpreterEnvironment implements Environment {
	
	private InterpreterEnvironment upper;
	private Map<String, IValue> d;
	
	private InterpreterEnvironment(InterpreterEnvironment e)
	{
		upper = e;
		d = new HashMap<String, IValue>();
	}
	
	public InterpreterEnvironment()
	{
		this(null);
	}

	@Override
	public Environment beginScope()
	{
		InterpreterEnvironment e = new InterpreterEnvironment(this);
		return e;
	}

	@Override
	public Environment endScope()
	{
		if (upper != null)
			upper = upper.upper;
		return this;
	}
	
	public void assoc(String id, IValue val) throws IdentiferDeclaredTwiceException
	{
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}
	
	public IValue find(String id) throws UndefinedIdException
	{		
		IValue v = d.get(id);
		if (v != null)
			return v;
		else if (upper != null)
			return upper.find(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}
}