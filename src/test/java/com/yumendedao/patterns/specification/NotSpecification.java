package com.yumendedao.patterns.specification;

public class NotSpecification<T> extends CompositeSpecification<T> {

	// 传递两个规格书之间的not操作
	private ISpecification<T> spec;

	public NotSpecification(ISpecification<T> _spec){
			this.spec = _spec;
		}

	@Override
	public boolean isSatisfiedBy(T candidate) {
		return !this.spec.isSatisfiedBy(candidate);
	}

}
