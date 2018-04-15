import java.util.LinkedList;

public class LinkedListSet implements Set {

	LinkedList<String> listSet;

	public LinkedListSet(){
		listSet = new LinkedList<>();
	}

	public void add(String element) {
		listSet.add(element);
	}

	public boolean query(String element) {
		return listSet.contains(element);
	}
}