package com.solid.l.good;

public class GoodSquare implements ShapeL{

	private int latura;
	public GoodSquare(int latura) {

		this.latura=latura;
	}


	@Override
	public String toString() {
		return "I am a square";
	}

	@Override
	public int getArea() {
		return latura*latura;
	}

	@Override
	public void setWidth(int width) {
		this.latura=width;
	}

	@Override
	public void setHeight(int height) {
		this.latura=height;
	}
}
