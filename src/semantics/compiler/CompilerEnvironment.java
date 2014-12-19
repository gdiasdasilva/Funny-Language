package semantics.compiler;

import java.util.HashMap;
import java.util.Map;

import semantics.Environment;
import semantics.IdentiferDeclaredTwiceException;
import semantics.UndefinedIdException;

public class CompilerEnvironment implements Environment<Id> {
	
	static int idCounter = 0;
	
	final int id;
	final int level;
	CompilerEnvironment upper;
	private Map<String, Id> d;
	
	public CompilerEnvironment() {
		id = -1; // empty environment
		level = -1;
		upper = null;
	}
	
	private CompilerEnvironment(CompilerEnvironment e) {
		id = idCounter++;
		upper = e;
		level = upper.level + 1;
		d = new HashMap<String, Id>();
	}

	@Override
	public Environment<Id> beginScope() {
		CompilerEnvironment e = new CompilerEnvironment(this);
		return e;
	}

	@Override
	public Environment<Id> endScope() {
		if (upper != null)
			upper = upper.upper;
		return this;
	}

	@Override
	public void assoc(String id, Id val) throws IdentiferDeclaredTwiceException {
		if (!d.containsKey(id))
			d.put(id, val);
		else
			throw new IdentiferDeclaredTwiceException();
	}

	@Override
	public Id find(String id) throws UndefinedIdException {
		Id v = d.get(id);
		if (v != null)
			return v;
		else if (upper != null)
			return upper.find(id);
		throw new UndefinedIdException("Undefined id '" + id + "' in this scope.");
	}
	
	public int getLevel() {
		return -1;
	}
	
	public String getUpperId() {
		if (level > 0)
			return upper.getId();
		return "";
	}
	
	public String getId() {
		return "frame_" + id;
	}
	
}
