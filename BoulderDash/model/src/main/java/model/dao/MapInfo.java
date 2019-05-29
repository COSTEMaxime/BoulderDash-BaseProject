package model.dao;

public class MapInfo {
	
	private String name;
	private String data;

	private int height;
	private int widht;
	private int minimumDiamondCount;
	
	public MapInfo(String name, String data, int height, int width, int minimumDiamondCount)
	{
		this.name = name;
		this.data = data.replaceAll("\n", "").replaceAll("\r", "");
		this.height = height;
		this.widht = width;
		this.minimumDiamondCount = minimumDiamondCount;
	}
	
	public String getName() {
		return name;
	}

	public String getData() {
		return data;
	}

	public int getHeight() {
		return height;
	}

	public int getWidht() {
		return widht;
	}

	public int getMinimumDiamondCount() {
		return minimumDiamondCount;
	}
}
