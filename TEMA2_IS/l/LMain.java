package com.solid.l;


import com.solid.l.good.*;

public class LMain {
	public static void main(String[] args) {
		testBadL();
	}

	private static void testBadL() {
		ShapeL actuallySquare = new GoodSquare(20);
		GoodGraphicsService badGraphicsService = new GoodGraphicsService();
		badGraphicsService.checkForArea(actuallySquare);
	}

}
