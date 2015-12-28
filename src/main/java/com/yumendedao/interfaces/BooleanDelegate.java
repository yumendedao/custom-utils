package com.yumendedao.interfaces;

/**
 * 真值委托, 调用委托代码返回布尔值
 * BooleanDelegate
 * @author wangwj<br>2015年3月27日
 */
public interface BooleanDelegate {
	public boolean determine(Object... params);
}
