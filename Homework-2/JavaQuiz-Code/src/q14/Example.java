package q14;

class Example {

	static class Pair {
		public Pair(int first, int second){
			this.first = first;
			this.second = second;
		}
		
		@Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + first;
      result = prime * result + second;
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Pair other = (Pair) obj;
      if (first != other.first)
        return false;
      if (second != other.second)
        return false;
      return true;
    }
    private int first;
		private int second;
	}
	
	public static void main(String[] args){
		Pair p1 = new Pair(3,4);
		Pair p2 = new Pair(7,8);
		Pair p3 = new Pair(3,4);
		
		if (p1.equals(p1)){ System.out.println("foo"); }
		if (p1.equals(p2)){ System.out.println("bar"); }
		if (p1.equals(p3)){ System.out.println("baz"); }
	}

	 
}