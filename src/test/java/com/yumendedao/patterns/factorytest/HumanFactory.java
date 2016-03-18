package com.yumendedao.patterns.factorytest;

public class HumanFactory extends AbstractHumanFactory {

	/**
	 * 根据class生产人，必须实现IHuman接口
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IHuman> T createHuuman(Class<T> c) {

		IHuman human = null;

		try {
			human = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			System.out.println("人种生产错误");
			e.printStackTrace();
		}

		if(human instanceof WhiteHuman){
			
		}
		return (T) human;
	}

}
