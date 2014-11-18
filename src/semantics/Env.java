package semantics;

import java.util.HashMap;
import java.util.Map;

public class Env implements IEnv {
	
	private Env upper;
	private Map<String, IValue> d;
	
	private Env(Env e)
	{
		upper = e;
		d = new HashMap<String, IValue>();
	}
	
	public Env()
	{
		this(null);
	}

	@Override
	public IEnv beginScope()
	{
		Env e = new Env(this);
		return e;
	}

	@Override
	public IEnv endScope()
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