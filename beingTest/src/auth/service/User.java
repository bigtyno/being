package auth.service;

public class User {

	private String id;
	private String name;
	private int level;

	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public User(String id, String name, int level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}

}
