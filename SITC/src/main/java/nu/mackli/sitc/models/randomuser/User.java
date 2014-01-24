package nu.mackli.sitc.models.randomuser;

/**
 * Created by macklinu on 12/15/13.
 */
public class User {
    Name name;
    Location location;
    String gender;
    String email;
    String password;
    String phone;
    String cell;
    String picture;

    @Override
    public String toString() {
        return name.toString();
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getPicture() {
        return picture;
    }

}
