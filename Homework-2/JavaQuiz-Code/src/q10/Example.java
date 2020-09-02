package q10;

class A {
	public void foo(){ 
		System.out.println("A.foo");
	}
}
class B extends A {
	public void foo(){ 
		System.out.println("B.foo");
	}
}

class Example {
	public static void main(String[] args){
		System.out.println("in example 10");
		
		Object[] os = new Object[3]; 
		os = new Object[]{ new A(), new Object(), new B() }; 
		
		for (Object o : os){
			if (o instanceof A){
				((A)o).foo();
			}  
		}
	}
}