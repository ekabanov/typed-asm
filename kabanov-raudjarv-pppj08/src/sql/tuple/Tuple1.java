package sql.tuple;

public class Tuple1<T1> implements Tuple {
	
	public final T1 v1;

	public Tuple1(T1 v1) {
		this.v1 = v1;
	}
	
	public T1 v1() {
		return v1;
	}

	// XXX temporary
	public String toString() {
		return "TUPLE1:"+String.valueOf(v1());
	}
	
}
