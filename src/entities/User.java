package entities;

public class User {
      
    private String username;
    private String password;
    private String phone;

    public User(String username, String password, String phone) {
    this.username = username;
    this.password = password;
    this.phone = phone;
    }

    public User(String username, String password) {
    this.username = username;
    this.password = password;
    }

    public User() {

    }

    public String getUsername() {

        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            '}';
    }
}
