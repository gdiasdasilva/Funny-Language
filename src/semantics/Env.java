package semantics;

import java.util.HashMap;
import java.util.Map;

public class Env implements IEnv {

//	private List<Map<String, IValue>> scopes;
	
	private Env upper;
	private Map<String, IValue> d;
	
//	public Env()
//	{
//		scopes = new ArrayList<Map<String, IValue>>();
//	}
	
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
//		scopes.add(new HashMap<String, IValue>());
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
	

//	@Override
//	public void assoc(String id, IValue val) throws IdentiferDeclaredTwiceException
//	{
//		if (!scopes.get(scopes.size()-1).containsKey(id))
//			scopes.get(scopes.size()-1).put(id, val);
//		else
//			throw new IdentiferDeclaredTwiceException();
//	}
	
	@Override
	public void assoc(String id, IValue val) throws IdentiferDeclaredTwiceException
	{
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}

//	@Override
//	public IValue find(String id) throws UndefinedIdException
//	{		
//		for(int i = scopes.size() - 1; i >= 0; i--)
//			if (scopes.get(i).containsKey(id))
//				return scopes.get(i).get(id);
//		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
//	}
	
	@Override
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