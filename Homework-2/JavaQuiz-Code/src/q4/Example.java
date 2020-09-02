package q4;


class A {
	void foo(){ 
		System.out.println("A.foo");
	}
	void bar(){ 
		System.out.println("A.bar");
		this.foo();
	}
}

class B extends A {
	void foo(){ 
		System.out.println("B.foo");
	}
	void bar(){ 
		System.out.println("B.bar");
		this.foo();
	}
}

class C extends B {
	void foo(){ 
		System.out.println("C.foo");
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("in example 4");
		
		A a = new C();
		a.bar(); 
	}
}