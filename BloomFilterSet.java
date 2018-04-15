public class BloomFilterSet implements Set {
	
	private boolean bits[];
	final private int k = 3;
	final private static int n = 150001;

	BloomFilterSet(){
		bits = new boolean[n];
		
		for(int i=0; i<n; i++){
			bits[i] = false;
		}
	}
	public void add(String element) {
		int index;

		for(int i=0; i<k; i++){
			index = calcHash(element, i);
			this.bits[index] = true;
		}
	}

	public boolean query(String element) {
		int index;
		boolean isPresent = true;
		
		for(int i=0; i<k; i++){
			index = calcHash(element, i);
			isPresent = isPresent && this.bits[index];
		}
		
		return isPresent;
	}

	private int calcHash(String element, int type){
		switch (type){
		case 0:
			return myCustomHash(element);
		case 1:
			return Math.abs(element.hashCode())%n;
		case 2:
			return myCustomHash_2(element);
		default:
			return 0;
		}
	}

	private int myCustomHash(String element){
		int hash = 0;
		
		for(int i=0; i<element.length(); i++){
			hash = hash ^ element.charAt(i);
		}
		
		return hash%n;
	}

	private int myCustomHash_2(String element){
		int hash = 7;
		
		for (int i = 0; i < element.length(); i++) {
			hash = hash*31 + element.charAt(i);
		}

		return (int)Math.sqrt(element.hashCode())%n;
	}
}