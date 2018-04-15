import java.util.UUID;

public class Experiment {
	
	public static void main(String args[]){
		
		int ARRAY_SIZE = 10000;
		String[] array = new String[ARRAY_SIZE];
		
		for(int i=0; i<ARRAY_SIZE; i++){
			array[i] = UUID.randomUUID().toString();
		}

		LinkedListSet linkedListSet = new LinkedListSet();
		BloomFilterSet bloomFilterSet = new BloomFilterSet();
		SkipListSet skipListSet = new SkipListSet();

		long timeSpent = 0, currentTime;
		
		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE/2; i++){
			linkedListSet.add(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average Time taken to add for linked set is: "+timeSpent*2/ARRAY_SIZE);

		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE/2; i++){
			bloomFilterSet.add(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average Time taken to add for bloom filter set is: "+timeSpent*2/ARRAY_SIZE);
		
		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE/2; i++){
			skipListSet.add(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average Time taken to add for skip list set is: "+timeSpent*2/ARRAY_SIZE);


		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE; i++){
			linkedListSet.query(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average time taken to query a linked set is: "+timeSpent/ARRAY_SIZE);

		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE; i++){
			bloomFilterSet.query(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average time taken to query a bloom filter set is: "+timeSpent/ARRAY_SIZE);
		
		currentTime = System.nanoTime();
		for(int i=0; i<ARRAY_SIZE; i++){
			skipListSet.query(array[i]);
		}
		timeSpent = System.nanoTime() - currentTime;

		System.out.println("Average time taken to query a skip list set is: "+timeSpent/ARRAY_SIZE);
		
		int bloomErrorCount = 0;
		for(int i=0; i<ARRAY_SIZE; i++){
			if(linkedListSet.query(array[i]) != bloomFilterSet.query(array[i])){
				bloomErrorCount++;
			}
		}
		
		int SkipListErrorCount = 0;
		for(int i=0; i<ARRAY_SIZE; i++){
			if(linkedListSet.query(array[i]) != skipListSet.query(array[i])){
				SkipListErrorCount++;
			}
		}
		System.out.println("The Bloom Filter error percentage is: "+(double)bloomErrorCount*100/ARRAY_SIZE);
		System.out.println("The Skip List error percentage is: "+(double)SkipListErrorCount*100/ARRAY_SIZE);
	}
}