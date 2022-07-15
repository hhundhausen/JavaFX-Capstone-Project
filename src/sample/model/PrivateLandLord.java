package sample.model;
import java.io.Serializable;
import java.util.Objects;

public class PrivateLandLord extends Review implements Serializable{
    private String mName;

    public PrivateLandLord(String type, String name, String companyName, String address,double communicationRating, double neighborhoodRating, double conditionRating, double overallRating, String rent, String notes)
    {
        super(type, companyName, address, communicationRating, neighborhoodRating, conditionRating, overallRating, rent, notes);
        mName = name;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateLandLord)) return false;
        if (!super.equals(o)) return false;
        PrivateLandLord that = (PrivateLandLord) o;
        return Objects.equals(getmName(), that.getmName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getmName());
    }

    @Override
    public String toString() {
        return "Private LandLord{" +
                "Private LandLord=" + mCompanyName +
                ", Address=" + mAddress +
                ", CommunicationRating=" + mCommunicationRating +
                ", NeighborhoodRating=" + mNeighborhoodRating +
                ", ConditionRating=" + mConditionRating +
                ", OverallRating=" + mOverallRating +
                ", RentPerMonth$" + mRent +
                ", Notes=" + mNotes +
                '}';
    }
}
