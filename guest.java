public class guest {
    private String Lname;
    private String Fname;
    private int age;
    private String sex;


    public guest(String Lname, String Fname, int age, String sex ){
        this.Lname = Lname;
        this.Fname = Fname;
        this.age = age;
        this.sex = sex;
    }
    public String getLname() {
        return Lname;
    }
    public String getFname() {
        return Fname;
    }
    public int getAge() {
        return age;
    }
    public String getSex() {
        return sex;
    }
}
