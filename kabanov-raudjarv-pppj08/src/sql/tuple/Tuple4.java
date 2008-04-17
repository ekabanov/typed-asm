package sql.tuple;

public class Tuple4<T1,T2,T3,T4> implements Tuple {
	private T1 v1;
	private T2 v2;
	private T3 v3;
	private T4 v4;
	
	public void set1(T1 v1) {
		this.v1 = v1;
	}
	public T1 get1() {
		return v1;
	}
	public void set2(T2 v2) {
		this.v2 = v2;
	}
	public T2 get2() {
		return this.v2;
	}	
	public void set3(T3 v3) {
		this.v3 = v3;
	}
	public T3 get3() {
		return this.v3;
	}
	public T4 get4() {
		return v4;
	}
	public void set4(T4 v4) {
		this.v4 = v4;
	}
}