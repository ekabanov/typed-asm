
public class Test {

  public static void main(String[] args) {
    Company c = new CompanyBuilder()
    .name("ZeroTurnaround")
    .address()
       .country("Estonia")
    .finish()
    .addEmployee()
      .firstName("Jevgeni")
      .lastName("Kabanov")
      .address()
        .country("Estonia")
      .finish()
    .finish()
    .toCompany();
  }

}
