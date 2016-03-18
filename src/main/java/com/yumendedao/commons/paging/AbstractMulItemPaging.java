package com.yumendedao.commons.paging;

import java.util.List;

import com.yumendedao.commons.paging.item.AbstractPageItem;

public abstract class AbstractMulItemPaging<T extends AbstractPageItem<T>> extends AbstractPaging<T> {

	public AbstractMulItemPaging(int pageSize) {
		super(pageSize);
	}
	
	@Override
	public final void setItems(List<T> items) {
		this.items.clear();
		if(items != null){
			this.items.addAll(items);
		}
		
	}
	
	@Override
	public final T remove(int index) {
		T item = get(index);
		T deleted = null;
		if(item != null){
			index = getPageCount() * pageSize + index;
			int count = 0;
			for (T t : items) {
				count += t.getItemCount();
				if(count > index){
					count -= t.getItemCount();
					break;
				}
			}
			deleted = item.remove(index - count);
			if(item.getItemCount() == 0 && items.remove(item)){
				deleted = item;
			}
		}
		return deleted;
	}
	
	@Override
	public T remove(T item) {
		return remove(query(item));
	}
	

	@Override
	public T get(int index){
		if(index < 0 || index > getTotalItemCount())
			return null;
		index = getPageCount() * pageSize + index;
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
			if(count > index)
				return t;
		}
		return null;
	}
	
	public T getItemAt(int index){
		if(index < 0 || index > getTotalItemCount())
			return null;
		index = getPageCount() * pageSize + index;
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
			if(count > index){
				count -= t.getItemCount();
				return t.get(index - count);
			}
		}
		return null;
	}
	
	@Override
	public boolean showPage(int pageIndex) {
		if (pageIndex > getPageCount() || pageIndex < 0)
			return false;
		currentPage = pageIndex;
		currentPageItems.clear();
		int index;
		for (int i = 0; i < pageSize; i++) {
			index = pageIndex * pageSize + i;
			if (isOutOfIndex(index)) {
				currentPageItems.add(blankItems.get(i));
			} else {
				currentPageItems.add(getItemAt(index));
			}
		}
		updateUI();
		return true;
	}
	
//	public boolean showChildItem(int itemIndex) {
//		if (itemIndex > items.size() || itemIndex < 0)
//			return false;
////		currentPage = itemIndex;
//		T item = currentPageItems.get(itemIndex);
//		int childItemCount = item.getItemCount();
//		int showChildItemCount = 0;
//		if(childItemCount == 0){
//			return true;
//		}
//		
//		int endIndex = childItemCount + itemIndex;
////		if()
////		showChildItemCount = endIndex >= pageSize ? childItemCount + itemIndex - pageSize : ;
//		if(childItemCount + itemIndex >= pageSize){
//			for (int i = showChildItemCount; i > 0; i--) {
//				currentPageItems.remove(currentPageItems.size());
//			}
//			for (T childItem : item.items) {
//				currentPageItems.add(childItem);
//			}
//		} else {
//			
//		}
//		currentPageItems.clear();
//		int index;
//		for (int i = 0; i < pageSize; i++) {
//			index = itemIndex * pageSize + i;
//			if (isOutOfIndex(index)) {
//				currentPageItems.add(blankItems.get(i));
//			} else {
//				currentPageItems.add(getItemAt(index));
//			}
//		}
//		updateUI();
//		return true;
//	}
	
	@Override
	public int getPageCount() {
		return getTotalItemCount() > 0 ? (getTotalItemCount() - 1) / pageSize : 0;
	}
	
//	@Override
//	public int query(T item) {
//		return super.query(item);
//	}
	
	public int getTotalItemCount() {
		int count = 0;
		for (T t : items) {
			count += t.getItemCount();
		}
		return count;
	}
	
	/**
	 * 更新显示,只显示currentPageItems中的内容即可
	 */
	@Override
	public abstract void updateUI();
}
