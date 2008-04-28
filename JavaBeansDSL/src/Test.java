
public class Test {

  public static void main(String[] args) {
    Company c = new CompanyBuilder<Builder>()
    .name("ZeroTurnaround")
    .address()
       .country("Estonia")
    .finish()
    .employee()
      .firstName("Jevgeni")
      .lastName("Kabanov")
      .address()
        .country("Estonia")
      .finish()
    .finish()
    .toCompany();
    
    System.out.println(c);
  }

}
