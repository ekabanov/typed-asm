package sql.tuple;

public class Tuple8<T1,T2,T3,T4,T5,T6,T7,T8> implements Tuple {
	private T1 v1;
	private T2 v2;
	private T3 v3;
	private T4 v4;
	private T5 v5;
	private T6 v6;
	private T7 v7;
	private T8 v8;
	
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
	public T5 get5() {
		return v5;
	}
	public void set5(T5 v5) {
		this.v5 = v5;
	}
	public T6 get6() {
		return v6;
	}
	public void set6(T6 v6) {
		this.v6 = v6;
	}
	public T7 get7() {
		return v7;
	}
	public void set7(T7 v7) {
		this.v7 = v7;
	}
	public T8 get8() {
		return v8;
	}
	public void set8(T8 v8) {
		this.v8 = v8;
	}
}