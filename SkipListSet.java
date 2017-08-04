class Node {
	String data;
	
	//Using the right and down approach
	Node rt, dn;

	public Node(String data, Node rt, Node dn) {
		this.data = data;
		this.rt = rt;
		this.dn = dn;
	}
}

public class SkipListSet implements Set {
	private Node head, level=null, last=null;

	/*	To make sure all the strings when
	 *  called on compareTo() are less than "max".
	 *  The "max" string contains 31 characters.
	 */
	static String max = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
	
	/* Initializing the Skip List with one level
	 * with a single node containing the maximum data. 
	 */
	public SkipListSet() {
		
		level = new Node("", null, null);
		level.rt = level.dn = level;
		
		last = new Node(max, null, null);
		last.rt = last;
		
		head = new Node(max, last, level);
	}

	/* Function to insert String into the Skip List */
	public void add(String x) {
		Node curr_level = head;
		level.data = x;

		while (curr_level != level) {
			
			//	Making sure the data is inserted into a sorted fashion
			while (curr_level.data.compareTo(x) < 0)
				curr_level = curr_level.rt;
			
			//	Increasing a node in the current level by one, if needed
			if (curr_level.dn.rt.rt.data.compareTo(curr_level.data) < 0) {
				curr_level.rt = new Node(curr_level.data, curr_level.rt, curr_level.dn.rt.rt);
				curr_level.data = curr_level.dn.rt.data;
			}
			else
				curr_level = curr_level.dn;
		}

		/* Increase the level of SkipList by one if necessary */
		if (head.rt != last)
			head = new Node(max, last, head);
	}
	
	/* Function to Query a String into the Skip List 
	 * Returns True if found, else False
	 */
	public boolean query(String element) {
		Node curr = head;
		boolean found = false;

		while (!found) {
			
			/*	If the current node is null, return false
			 * 	This covers the following cases:
			 1. The Skip List is Empty.
			 2. We reached the end of the Skip List.
			 */
			if (curr == null) {
				return found;
			}				
			
			/* 	Found it  */
			else if (curr.data.compareTo(element) == 0) {
				found = true;
			}
			
			/*	If the data in the right node is greater than the key  */
			else if (curr.rt != null && curr.rt.data.compareTo(element) <= 0) {
				if (curr == curr.rt)
					return false;
				curr = curr.rt;
			}
			
			// Else case
			else {
				if (curr == curr.dn)
					return false;
				curr = curr.dn;
			}
		}
		
		return found;
	}

	//utility Function to print the current contents of the skip list
	public void print() {
		Node curr_level = head;

		while( curr_level.dn != level )
			curr_level = curr_level.dn;

		while (curr_level.rt != last ) {
			System.out.print(curr_level.data +"\n");
			curr_level = curr_level.rt;
		}
	}
	
	//Test Main method
	public static void main(String args[]) {
		SkipListSet s = new SkipListSet();
		
		s.add("Aman");
		s.add("Agarwal");
		s.add("Aa");
		s.add("Bb");
		
		System.out.println(s.query("Agarw"));
		System.out.println(s.query("Ag"));
		System.out.println(s.query("Aman1"));
		System.out.println(s.query("Aman"));
		System.out.println(s.query("AA"));
		System.out.println(s.query("Aa"));
		System.out.println(s.query("B"));
		System.out.println(s.query("Bb"));
		
		System.out.println();
		s.print();
	}
}