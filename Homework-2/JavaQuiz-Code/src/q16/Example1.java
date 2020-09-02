package q16;

import java.util.ArrayList;

 
class Example1 {

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
		ArrayList<Pair> x = new ArrayList<Pair>();
		x.add(new Pair(3,4));
		x.add(new Pair(7,8));
		
		print(x);
	}

	private static void print(ArrayList<Pair> list) {
		for (int i=0; i < list.size(); i++){
			System.out.println(list.get(i).toString());
		}
	}
}