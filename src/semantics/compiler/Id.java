package semantics.compiler;

public class Id {
	final String id;
	final String type;
	final int level;
	
	Id(String id, String type, int level) {
		this.id = id;
		this.type = type;
		this.level = level;
	}
}
