package q9;

class A {
	public void foo(){ 
		System.out.println("A.foo");
	}
	public void bar(){ 
		System.out.println("A.bar"); 
	}
}
class B extends A {
	public void foo(){ 
		System.out.println("B.foo");
	}
	public void bar(){ 
		System.out.println("B.bar"); 
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("in example 9");
		A[] as = new A[2]; 
		as[0] = new A(); 
		as[1] = new B();
		for (A a : as){
			a.foo();
		}
	}
}