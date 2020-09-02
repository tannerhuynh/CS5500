package q12;

class Example {

	static class Pair {
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		private int first;
		private int second;
	}

	public static void main(String[] args) {
		Pair p1 = new Pair(3, 4);
		Pair p2 = new Pair(7, 8);
		Pair p3 = new Pair(3, 4);

		if (p1 == p1) {
			System.out.println("foo");
		}
		if (p1 == p2) {
			System.out.println("bar");
		}
		if (p1 == p3) {
			System.out.println("baz");
		}

	}

}