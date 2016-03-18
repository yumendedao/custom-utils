package com.yumendedao.patterns.specification;

public abstract class CompositeSpecification<T> implements ISpecification<T> {

	@Override
	public abstract boolean isSatisfiedBy(T candidate);

	@Override
	public ISpecification<T> and(ISpecification<T> spec) {
		return new AndSpecification<T>(this, spec);
	}

	@Override
	public ISpecification<T> or(ISpecification<T> spec) {
		return new OrSpecification<T>(this, spec);
	}

	@Override
	public ISpecification<T> not() {
		return new NotSpecification<T>(this);
	}

}
