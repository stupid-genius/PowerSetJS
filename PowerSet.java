/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import java.util.*;
import java.util.stream.IntStream;

public class PowerSet<T> implements Set<T>, Iterable<T>, Iterator<Set<T>>{
	// TODO enforce no duplicates
	// TODO limit size to ?
	private final ArrayList<T> elements = new ArrayList<>();
	private long cur;
	private long max;
	private long maxOff;
	private HashSet<T> subset;

	public PowerSet(){}
	public PowerSet(Collection<T> elements) {
		this.elements.addAll(elements);
	}

	@Override
	public int size(){
		return elements.size();
	}

	@Override
	public boolean isEmpty(){
		return elements.isEmpty();
	}

	@Override
	public boolean contains(Object o){
		return elements.contains(o);
	}

	@Override
	public Iterator<T> iterator(){
		return elements.iterator();
	}


	@Override
	public Object[] toArray(){
		return new Object[0];
	}

	@Override
	public <T1> T1[] toArray(T1[] t1s){
		return (T1[]) elements.toArray();
	}

	@Override
	public boolean add(T t){
		return elements.add(t);
	}

	@Override
	public boolean remove(Object o){
		if(o != null) {
			return elements.remove(o);
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> collection){
		return elements.containsAll(collection);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection){
		return elements.addAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection){
		return elements.retainAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection){
		return elements.removeAll(collection);
	}

	@Override
	public void clear(){
		elements.clear();
	}

	public PowerSet powersetIterator(){
		cur = 0;
		max = (long) Math.pow(2, elements.size());
		maxOff = elements.size();
		subset = new HashSet<T>();
		return this;
	}

	@Override
	public boolean hasNext(){
		return cur<max;
	}

	@Override
	public Set<T> next(){
		subset.clear();
		for(int off=0; off<maxOff; ++off){
			long mask = 1L << off;
			if((cur & mask) != 0){
				subset.add(elements.get(off));
			}
		}
		++cur;
		return subset;
	}

	public static void main(String[] args){
		System.out.println("PowerSet demo");
		ArrayList<Integer> test = new ArrayList<>();
		IntStream.range(1, 30).forEach(test::add);
		PowerSet<Integer> ps = new PowerSet<>(test);

		ps.powersetIterator();
		Set<Integer> sub;
		long start = System.nanoTime();
		while(ps.hasNext()){
			sub = ps.next();
//			System.out.println(sub.toString());
		}
		long end = System.nanoTime() - start;
		System.out.println("took (ms): "+end/1000000);
	}
}
