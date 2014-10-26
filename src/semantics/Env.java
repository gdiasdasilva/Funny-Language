package semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Env implements IEnv {

	String id;
	
	List<Map<String, IValue>> scopes;

	public Env()
	{
		scopes = new ArrayList<Map<String, IValue>>();
	}

	@Override
	public void beginScope()
	{
		scopes.add(new HashMap<String, IValue>());
	}

	@Override
	public void endScope()
	{
		scopes.remove(scopes.size()-1);
	}

	@Override
	public void assoc(String id, IValue val)
	{
		scopes.get(scopes.size()-1).put(id, val);
	}

	@Override
	public IValue find(String id) throws UndefinedIdException
	{		
		for(int i = scopes.size() - 1; i >= 0; i--)
			if (scopes.get(i).containsKey(id))
				return scopes.get(i).get(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}
}