package sample.model;

import java.io.Serializable;
import java.util.Objects;

public class PropertyManagementCompanies extends Review implements Serializable {
    private String mManagementCO;

    public PropertyManagementCompanies(String type, String manName, String companyName, String address,double communicationRating, double neighborhoodRating, double conditionRating, double overallRating, String rent, String notes)
    {
        super(type, companyName, address, communicationRating, neighborhoodRating, conditionRating, overallRating, rent, notes);
        mManagementCO = manName;
    }

    public String getmManagementCO() {
        return mManagementCO;
    }

    public void setmManagementCO(String mManagementCO) {
        this.mManagementCO = mManagementCO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyManagementCompanies)) return false;
        if (!super.equals(o)) return false;
        PropertyManagementCompanies that = (PropertyManagementCompanies) o;
        return Objects.equals(getmManagementCO(), that.getmManagementCO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getmManagementCO());
    }


    @Override
    public String toString() {
        return "Property Management Company{" +
                "Management Company=" + mCompanyName +
                ", Address='" + mAddress +
                ", CommunicationRating=" + mCommunicationRating +
                ", NeighborhoodRating=" + mNeighborhoodRating +
                ", ConditionRating=" + mConditionRating +
                ", OverallRating=" + mOverallRating +
                ", RentPerMonth$" + mRent +
                ", Notes=" + mNotes +
                '}';
    }
}
