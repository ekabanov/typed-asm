package sql.tuple;

public class Tuple2<T1, T2> implements Tuple {
	public final T1 v1;
	public final T2 v2;

	public Tuple2(T1 v1, T2 v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public T1 v1() { return v1; }
	public T2 v2() { return v2; }

	
	
	
	
	
	
	
	
	
	
	// XXX temporary
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Tuple2. 1=");
		sb.append(v1());
		
		sb.append(" 2=");
		sb.append(v2());
		
		return sb.toString();
	}
}
