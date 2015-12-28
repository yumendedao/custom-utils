package com.yumendedao.commons.paging;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: AbstractPaging
 * @Description: 分页信息，必须指定页面大小，页面中只有相同类型的数据,一条数据占一行
 * @author guxingchun
 * @date 2015年12月21日 下午5:50:39
 * @param <T>
 */
public abstract class AbstractPaging<T> implements IPaging<T> {

	protected final List<T> items = new LinkedList<T>();
	protected final List<T> currentPageItems = new LinkedList<T>();
	protected final List<T> blankItems = new LinkedList<T>();
	protected int currentPage = 0;
	protected int pageSize = 0;
	protected int lastIndex = -1;
	
//	protected boolean isCache = true;
	// protected boolean series = true;

	public AbstractPaging(int pageSize) {
		if(pageSize <= 0)
			pageSize = 1;
		this.pageSize = pageSize;
		initBlackItems();
	}

	@SuppressWarnings("unused")
	private AbstractPaging() {
	}
	
	@Override
	public boolean addItem(int index, T item, boolean isRefresh) {
		boolean isSuccess = false;
		if(item == null)
			return false;
		if(!isOutOfIndex(index)){
			items.add(index, item);
			isSuccess = true;
		} else {
			if(index == getItemCount())
				isSuccess = items.add(item);
		}
		if(isRefresh)
			showEnd();
		return isSuccess;
	}

	@Override
	public synchronized boolean addItem(int index, T item) {
		return addItem(index, item, true);
	}
	
	@Override
	public synchronized boolean addItem(T item, boolean isRefresh) {
		return addItem(items.size(),item, isRefresh);
	}
	
	@Override
	public synchronized boolean addItem(T item){
		return addItem(item, true);
	}

	@Override
	public T remove(int index) {
		if (!isOutOfIndex(index)) {
			T remove = items.remove(index);
			freshen();
			return remove;
		} else {
			return null;
		}
	}

	@Override
	public T remove(T item) {
		return remove(query(item));
	}
	
	@Override
	public int query(T item) {
		int index = -1;
		if(item == null)
			return index;
		T temp = null;
		for (int i = 0; i < items.size(); i++) {
			temp = items.get(i);
			if (temp != null && temp.equals(item)) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public T get(int index) {
		if (!isOutOfIndex(index))
			return items.get(index);
		return null;
	}

	@Override
	public boolean update(int index, T item) {
		if (!isOutOfIndex(index)) {
			items.set(index, item);
			freshen();
			return true;
		}
		return false;
	}
	
	@Override
	public abstract boolean isEquals(T obj1, T Obj2);

	@Override
	public final void freshen() {
		showPage(currentPage);
	}

	@Override
	public final boolean pageUp() {
		if (currentPage <= 0)
			return false;
		else {
			showPage(--currentPage);
			return true;
		}
	}

	@Override
	public final boolean pageDown() {
		if (currentPage >= getPageCount() || currentPage < 0)
			return false;
		else {
			showPage(++currentPage);
			return true;
		}
	}
	
	@Override
	public boolean showFirst() {
		return showPage(0);
	}
	
	@Override
	public boolean showEnd() {
		return showPage(getPageCount());
	}

	@Override
	public boolean showPage(final int pageIndex) {
		// boolean isSuccess = false;
		if (pageIndex > getPageCount())
			return false;
		currentPage = pageIndex;
		currentPageItems.clear();
		int index;
		for (int i = 0; i < pageSize; i++) {
			index = pageIndex * pageSize + i;
			if (isOutOfIndex(index)) {
				currentPageItems.add(blankItems.get(i));
			} else {
				currentPageItems.add(items.get(index));
			}
		}
		updateUI();
		return true;
	}

	@Override
	public final void cleanItem() {
		items.clear();
		currentPage = 0;
		lastIndex = -1;
		currentPageItems.clear();
		for (T blank : blankItems) {
			currentPageItems.add(blank);
		}
		updateUI();
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	@Override
	public int getPageCount() {
		return items.size() > 0 ? (items.size() - 1) / pageSize : 0;
	}

	@Override
	public abstract boolean isBlank(T item);

	protected final void initBlackItems() {
		for (int i = 0; i < pageSize; i++) {
			blankItems.add(getBlank());
		}
	}

	protected boolean isOutOfIndex(int index) {
		if (index < 0 || index >= getItemCount())
			return true;
		else
			return false;
	}

	protected final void filling() {
		// if (currentPageItems.size() < pageSize) {
		for (int i = currentPageItems.size(); i < pageSize; i++) {
			currentPageItems.add(blankItems.get(i));
		}
		// }
	}

	protected List<T> getListByPageIndex(final int pageIndex) {
		List<T> list = null;
		if (items.size() > 0 && pageIndex > getPageCount()) {
			list = new LinkedList<T>();
			for (int i = pageIndex * pageSize; i < pageSize; i++) {
				if (i > items.size())
					break;
				list.add(items.get(i));
			}
		}
		return list;
	}

}
