package auth.service;

public class User {

	private String id;
	private String name;
	private int lvl;

//관리자용	
	public User(String id, String name, int lvl) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;
	}
//일반
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
