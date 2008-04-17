package sql.tuple;


public class Tuple3<T1,T2,T3> implements Tuple {
	public final T1 v1;
	public final T2 v2;
	public final T3 v3;
	
	public Tuple3(T1 v1, T2 v2, T3 v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	public T1 v1() {
		return v1;
	}
	public T2 v2() {
		return this.v2;
	}	
	public T3 v3() {
		return this.v3;
	}
}