package article.model;

public class Writer {

	private String id;
	private String name;
	private int lvl;
	
	public Writer(String id, String name, int lvl) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;
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

}
