package Managing;

 class Person {
    protected String name;
    protected int age;
    protected String email;


    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

   public String toString()
   {
       return "Name: " + this.name + "\nAge: " + this.age + "\ne-mail: " + this.email;
   }

     public String getName() {
         return name;
     }

     public int getAge() {
         return age;
     }

     public String getEmail() {
         return email;
     }
 }
