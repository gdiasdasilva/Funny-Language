package semantics;

import java.util.HashMap;
import java.util.Map;
import semantics.Environment;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class EnvironmentImpl<T> implements Environment<T> {
	
	private EnvironmentImpl<T> upper;
	private Map<String, T> d;
	
	private EnvironmentImpl(EnvironmentImpl<T> e)
	{
		upper = e;
		d = new HashMap<String, T>();
	}
	
	public EnvironmentImpl()
	{
		this(null);
	}

	@Override
	public Environment<T> beginScope()
	{
		EnvironmentImpl<T> e = new EnvironmentImpl<T>(this);
		return e;
	}

	@Override
	public Environment<T> endScope()
	{
		if (upper != null)
			upper = upper.upper;
		return this;
	}
	
	@Override
	public void assoc(String id, T val) throws IdentiferDeclaredTwiceException
	{
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}
	
	@Override
	public T find(String id) throws UndefinedIdException
	{		
		T v = d.get(id);
		if (v != null)
			return v;
		else if (upper != null)
			return upper.find(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}
}