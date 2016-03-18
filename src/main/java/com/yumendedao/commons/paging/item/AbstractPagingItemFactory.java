package com.yumendedao.commons.paging.item;

import com.yumendedao.commons.paging.AbstractPaging;

public abstract class AbstractPagingItemFactory<T extends IPageItem<?>> extends AbstractPaging<T> {

	public AbstractPagingItemFactory(int pageSize) {
		super(pageSize);
	}

	public abstract T createItem();
}
