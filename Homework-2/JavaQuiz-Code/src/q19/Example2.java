package q19;

import java.util.ArrayList;
import java.util.List;
 
class Example2 {

	static class Pair {
		public Pair(int first, int second){
			this.first = first;
			this.second = second;
		}
		
		public String toString(){
			return "<" + first + "," + second + ">";
		}
		
		private int first;
		private int second;
	}
	
	public static void main(String[] args){
		List<Pair> x = new ArrayList<Pair>();
		x.add(new Pair(3,4));
		x.add(new Pair(7,8));
		
		print(x);
	}

	private static <T> void print(List<T> list) {
		list.forEach(t -> System.out.println(t));
	}
}