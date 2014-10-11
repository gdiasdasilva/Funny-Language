package semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IEnv<T> implements Env<T> {

	String id;
	
	List<Map<String, Value<T>>> scopes;

	public IEnv()
	{
		scopes = new ArrayList<Map<String, Value<T>>>();
	}

	@Override
	public void beginScope()
	{
		scopes.add(new HashMap<String, Value<T>>());
	}

	@Override
	public void endScope()
	{
		scopes.remove(scopes.size()-1);
	}

	@Override
	public void assoc(String id, Value<T> val)
	{
		scopes.get(scopes.size()-1).put(id, val);
	}

	@Override
	public Value<T> find(String id)
	{		
		for(int i = scopes.size()-1; i >= 0; i--)
		{
			if (scopes.get(i).containsKey(id))
				return (Value<T>) scopes.get(i).get(id);
		}
		
		return null;
	}
}