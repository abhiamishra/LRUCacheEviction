//NAME: ABHISHEK AMOL MISHRA AND NETID: AAM190011
//Here we create a GenericHashMap with takes in two generic variables, T as the key
//K for the value
public class GenericHashMap<T, K> {
	/*Here we create our field variables for our hash map:
	 * - The size variable holds the size of our hash map
	 * - The loadFactor variable holds the load size so that we know when to resize
	 * - The bucket variable holds the amount of new elements we add to our hash map*/
	private int size = 11;
	private double loadFactor = 0.5;
	private int bucket = 0;
	
	/*Here we have an array called hashMap that will hold an array of Buckets
	 * which allows us to store the value and the key at the same time. This array
	 * works as our hashmap*/
	private Bucket<T, K>[] hashMap = (Bucket<T, K>[]) new Bucket<?,?>[size];
	
	//Here we have an inner class called Bucket that will
	//store the key - T - and value - K - passed in by the GenericHashMap.
	//This bucket class allows us to hold the key and value as on object
	//allowing us to make a more fluid and easy-to-use hash map.
	static class Bucket<T, K>{
		//We simply create our two variables from the generic variables that
		//will be passed in and we create getters and setters along with a constructor
		//to create a Bucket object in the first place
		private T key;
		private K value;
		
		public Bucket(T key, K value) {
			this.key = key;
			this.value = value;
		}
		public T getKey() {
			return key;
		}
		public void setKey(T key) {
			this.key = key;
		}
		public K getValue() {
			return value;
		}
		public void setValue(K value) {
			this.value = value;
		}
	}
	
	//Here is our implemented put function for our hashmap. It takes 
	//in a key of type T and a value of type K.
	public void put(T key, K data) {
		//We first insert the key and value in our array by calling
		//the hashing which places the key and data - according to its hashCode
		//in to the hashMap array
		hashing(hashMap, key, data);
		
		//After that, we increment our bucket variable because
		//we have an added a new element into our hashmap
		bucket++;
		
		//We calculate our load ratio by dividing the num. of elements by the size
		double load = (double)bucket/(double)size;
		
		//If the load is bigger than or equal to the loadfactor, then we need to resize and rehash
		if(load >= loadFactor) {
			//We call the resize function and return an array of Bucket<T,K> in essence
			//making a new array and setting hashMap to this new, bigger, rehashed array
			hashMap = this.resize();
			//Finally, we store the length of the hashMap in the size variable
			size = hashMap.length;
		}
	}
	
	//Here we have our hashMap's get function which gets the K object from the T Key passed in
	public K get(T key) {
		//We first calculate the hashcode from the key passed in by calling the
		// .hashCode() and finding the hashcode % length of hashMap.
		int hashCode = (Math.abs(key.hashCode())) % hashMap.length;
		
		//Here we have a variable called count that will allow us to 
		int count=0;
		
		//Here we have a result Bucket that will store the 
		//the value from the key that we access the hash map
		Bucket<T, K> result = new Bucket<T, K>(key, null);
		
		//Here we have a boolean variable that will allow us to keep
		//going through the hash map until we find our value
		boolean cont = true;
		
		//We only continue searching if the count variable is less than our length
		//our hashcode is less than our length and that our boolean variable to continue
		//is true.
		while(count < hashMap.length && hashCode < hashMap.length && cont) {
			//If the place in the hashmap at the hashcode is not null
			if(hashMap[hashCode] != null) {
				//We first look if the key of the object equals the key we are searching for.
				if(hashMap[hashCode].getKey().equals(key)) {
					//We make result equal to the object stored at the hashcode index in the hashmap
					result = hashMap[hashCode];
					
					//we set cont to false because we've found what we wanted to find and no
					//longer need to look
					cont = false;
				}
				else {
					//If we didn't find our key in that place, we need to look
					//in another location so we use double hashing to
					//access another location
					
					//We increment our count so that we can double-hash
					count++;
					//We make another hashcode with the formula from Zybooks
					int hashCode2 = 13 - (Math.abs(key.hashCode())) % 13;
					//Finally, we use the double hash formula to get the new hashcode
					//and continue searching
					hashCode = (hashCode + count*hashCode2)%hashMap.length;
				}
			}
			else {
				//If we didn't find our key in that place, we need to look
				//in another location so we use double hashing to
				//access another location
				
				//We increment our count so that we can double-hash
				count++;
				//We make another hashcode with the formula from Zybooks
				int hashCode2 = 13 - (Math.abs(key.hashCode())) % 13;
				//Finally, we use the double hash formula to get the new hashcode
				//and continue searching
				hashCode = (hashCode + count*hashCode2)%hashMap.length;
			}
		}
		
		//Finally, we return the Bucket's value/object
		return result.getValue();
	}
	
	private void hashing(Bucket<T, K> [] hashMap, T key, K data) {
		//We first calculate the hashcode from the key passed in by calling the
		// .hashCode() and finding the hashcode % length of hashMap.
		int hashCode = (Math.abs(key.hashCode())) % hashMap.length;
		
		//Here we have a variable called count that will allow us to 
		int count=0;
		
		//Here we have a boolean variable that will allow us to keep
		//going through the hash map until we find our value
		boolean cont = true;
		
		//We only continue searching if the count variable is less than our length
		//our hashcode is less than our length and that our boolean variable to continue
		//is true.
		while((count < hashMap.length && hashCode < hashMap.length && cont==true)==true) {
			if(hashMap[hashCode] == null) {
				//If the place in the hashmap by the index of hashcode is null - there's nothing there
				//Then we set that place in array to a new Bucket object with constructor
				//of Bucket with the key and data being its parameters
				hashMap[hashCode] = new Bucket<T, K>(key, data);

				//we set cont to false because we've found what we wanted to find and no
				//longer need to look
				cont = false;
			}
			else {
				//If we didn't find our key in that place, we need to look
				//in another location so we use double hashing to
				//access another location
				
				//We increment our count so that we can double-hash
				count++;
				//We make another hashcode with the formula from Zybooks
				int hashCode2 = 13 - (Math.abs(key.hashCode())) % 13;
				//Finally, we use the double hash formula to get the new hashcode
				//and continue searching
				hashCode = (hashCode + count*hashCode2)%hashMap.length;
			}
		}
	}
	
	private Bucket<T, K>[] resize() {
		//In this resize function, we first
		//call the nextPrime() that gets the next
		//highest prime number from our current size.
		int newSize = this.nextPrime();
		
		//Here we create a new hash map array with our new size that we got from the 
		//nextPrime function
		Bucket<T, K>[] newGenericHashMap = (Bucket<T, K>[]) new Bucket<?,?>[newSize];
		
		//We make a counter variable called probe that will
		//increment through our original hash map, get the object and then
		//we will put that object into our new array - in essence rehashing it
		int probe = 0;
		while(probe < hashMap.length) {
			//While our probe is less than the original hash map's length,
			//we access that location and if it is not null,
			//we get the Bucket in that location in the array.
			if(hashMap[probe] != null) {
				Bucket<T,K> data = hashMap[probe];
				
				//Finally, we recall the hashing function with the
				//new hashmap and the bucket we got from our original
				//hashmap
				hashing(newGenericHashMap, data.getKey(), data.getValue());
			}
			
			//We increase the probe variable to access the elements
			//of the hashmap
			probe++;
		}
		
		//Finally, we return the new hashmap 
		return newGenericHashMap;
	}
	
	private int nextPrime() {
		//In the nextPrime function, we first
		//double our current size and then
		//we start finding the next prime number from that 
		//size
		int newSize=size*2;
		while(newSize%2==0) {
			//While we have an even number, we 
			//will add one. This will eventually
			//lead us to an odd number - stopping the
			//loop and getting to our next prime number
			newSize++;
		}
		
		//finally, we return this new size for our new array
		return newSize;
	}
}

