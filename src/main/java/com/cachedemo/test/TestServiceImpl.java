package com.cachedemo.test;

import java.util.List;

public class TestServiceImpl implements TestService {
	public List getAllObject() {
		System.out.println("---TestService：Cache 内不存在该 element，查找并放入 Cache！");
		return null;
	}

	public void updateObject(Object Object) {
		System.out
				.println("---TestService：更新了对象，这个 Class 产生的 cache 都将被 remove！");

	}

}
