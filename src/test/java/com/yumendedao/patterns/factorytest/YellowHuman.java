package com.yumendedao.patterns.factorytest;

public class YellowHuman implements IHuman {

	@Override
	public void getColor() {
		System.out.println("color is yellow");

	}

	@Override
	public void talk() {
		System.out.println("黄种人说话");
	}

}
