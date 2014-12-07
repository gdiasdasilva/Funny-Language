package semantics.typeSystem;

import java.util.HashMap;
import java.util.Map;

import semantics.Environment;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class TypeEnvironment implements Environment {

	private TypeEnvironment upper;
	private Map<String, Type> d;
	
	private TypeEnvironment(TypeEnvironment e)
	{
		upper = e;
		d = new HashMap<String, Type>();
	}
	
	public TypeEnvironment()
	{
		this(null);
	}

	@Override
	public Environment beginScope()
	{
		TypeEnvironment e = new TypeEnvironment(this);
		return e;
	}

	@Override
	public Environment endScope()
	{
		if (upper != null)
			upper = upper.upper;
		return this;
	}
	
	public void assoc(String id, Type val) throws IdentiferDeclaredTwiceException
	{
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}
	
	public Type find(String id) throws UndefinedIdException
	{		
		Type v = d.get(id);
		if (v != null)
			return v;
		else if (upper != null)
			return upper.find(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}

}
