package DataTable;
import DataTable.Datasources;
public class User {
    private int id;
    int age,isLoggedIn;
    String gender,name,type;
    private String email,password;
    String city,bio;
    Datasources datasource;

    public User(int age, int isLoggedIn, String gender, String name, String type, String email, String password, String city, String bio){
        this.age = age;
        this.isLoggedIn = isLoggedIn;
        this.gender = gender;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
        this.city = city;
        this.bio = bio;
        this.datasource = new Datasources();
    }

    public void userRegistration(){
        if(!datasource.open()){
            System.out.println("Can't open the database");
            return;
        }
        datasource.close();
    }

    public void userLogin(){
        if(!datasource.open()){
            System.out.println("Can't open the database");
            return;
        }
        datasource.close();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(int isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
