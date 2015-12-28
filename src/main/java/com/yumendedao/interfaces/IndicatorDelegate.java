package com.yumendedao.interfaces;

/**
 * 指示器委托, 调用委托代码获得状态值
 * IndicatorDelegate
 * @author wangwj<br>2015年3月27日
 */
public interface IndicatorDelegate {
	public Object determine(Object... params);
}
