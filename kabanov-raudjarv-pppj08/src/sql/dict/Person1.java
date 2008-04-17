package sql.dict;

import java.util.Date;

public class Person1 implements Table1 {

	public String getName() { return "person"; };
	
	public static final Person1 TABLE = new Person1();

	public static final Column2<Person1, String> NAME = new Column2<Person1, String>(TABLE, "name", String.class);
	public static final Column2<Person1, Integer> HEIGHT = new Column2<Person1, Integer>(TABLE, "height", Integer.class);
	public static final Column2<Person1, Date> BIRTHDAY = new Column2<Person1, Date>(TABLE, "birthday", Date.class);
	
}
