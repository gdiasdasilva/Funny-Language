package semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Env implements IEnv {

	String id;
	
	List<Map<String, Value>> scopes;

	public Env()
	{
		scopes = new ArrayList<Map<String, Value>>();
	}

	@Override
	public void beginScope()
	{
		scopes.add(new HashMap<String, Value>());
	}

	@Override
	public void endScope()
	{
		scopes.remove(scopes.size()-1);
	}

	@Override
	public void assoc(String id, Value val)
	{
		scopes.get(scopes.size()-1).put(id, val);
	}

	@Override
	public Value find(String id)
	{		
		for(int i = scopes.size()-1; i >= 0; i--)
		{
			if (scopes.get(i).containsKey(id))
				return (Value) scopes.get(i).get(id);
		}
		
		return null;
	}
}