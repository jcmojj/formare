package br.com.clinicaformare.bean.entity;

import java.util.function.Supplier;

public class Container<T> {
	 private Supplier<T> supplier;

	 Container(Supplier<T> supplier) {
	    this.supplier = supplier;
	  }

	  T createContents() {
	    return supplier.get();
	  }
}
