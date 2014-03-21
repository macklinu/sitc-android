package nu.mackli.sitc.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macklinu on 3/21/14.
 */
public class RegistrationUserData implements Parcelable {
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String phoneNumber;
    public String username;
    public String email;
    public String password;

    public RegistrationUserData() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    private RegistrationUserData(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.dateOfBirth = in.readString();
        this.phoneNumber = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public static Parcelable.Creator<RegistrationUserData> CREATOR = new Parcelable.Creator<RegistrationUserData>() {
        public RegistrationUserData createFromParcel(Parcel source) {
            return new RegistrationUserData(source);
        }

        public RegistrationUserData[] newArray(int size) {
            return new RegistrationUserData[size];
        }
    };
}
