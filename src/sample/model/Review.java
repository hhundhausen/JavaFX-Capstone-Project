package sample.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class Review implements Serializable, Comparable{
    protected String mType;
    protected String mCompanyName;
    protected String mAddress;
    protected double mCommunicationRating;
    protected double mNeighborhoodRating;
    protected double mConditionRating;
    protected double mOverallRating;
    protected String mRent;
    protected String mNotes;

    public Review(String type, String companyName, String address, double communicationRating, double neighborhoodRating,
                  double conditionRating, double overallRating, String rent, String notes)
    {
        mType = type;
        mCompanyName = companyName;
        mAddress = address;
        mCommunicationRating = communicationRating;
        mNeighborhoodRating = neighborhoodRating;
        mConditionRating = conditionRating;
        mOverallRating = overallRating;
        mRent = rent;
        mNotes = notes;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmCompanyName() {
        return mCompanyName;
    }

    public void setmCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public double getmCommunicationRating() {
        return mCommunicationRating;
    }

    public void setmCommunicationRating(double mCommunicationRating) {
        this.mCommunicationRating = mCommunicationRating;
    }

    public double getmNeighborhoodRating() {
        return mNeighborhoodRating;
    }

    public void setmNeighborhoodRating(double mNeighborhoodRating) {
        this.mNeighborhoodRating = mNeighborhoodRating;
    }

    public double getmConditionRating() {
        return mConditionRating;
    }

    public void setmConditionRating(double mConditionRating) {
        this.mConditionRating = mConditionRating;
    }

    public double getmOverallRating() {
        return mOverallRating;
    }

    public void setmOverallRating(double mOverallRating) {
        this.mOverallRating = mOverallRating;
    }

    public String getmRent() {
        return mRent;
    }

    public void setmRent(String mRent) {
        this.mRent = mRent;
    }

    public String getmNotes() {
        return mNotes;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Double.compare(review.getmCommunicationRating(), getmCommunicationRating()) == 0 &&
                Double.compare(review.getmNeighborhoodRating(), getmNeighborhoodRating()) == 0 &&
                Double.compare(review.getmConditionRating(), getmConditionRating()) == 0 &&
                Double.compare(review.getmOverallRating(), getmOverallRating()) == 0 &&
                Objects.equals(getmType(), review.getmType()) &&
                Objects.equals(getmCompanyName(), review.getmCompanyName()) &&
                Objects.equals(getmAddress(), review.getmAddress()) &&
                Objects.equals(getmRent(), review.getmRent()) &&
                Objects.equals(getmNotes(), review.getmNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmType(), getmCompanyName(), getmAddress(), getmCommunicationRating(), getmNeighborhoodRating(), getmConditionRating(), getmOverallRating(), getmRent(), getmNotes());
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getInstance();
        return "Review{" +
                "Type=" + mType +
                ", CompanyName=" + mCompanyName +
                ", Address='" + mAddress +
                ", CommunicationRating=" + mCommunicationRating +
                ", NeighborhoodRating=" + mNeighborhoodRating +
                ", ConditionRating=" + mConditionRating +
                ", OverallRating=" + mOverallRating +
                ", RentPerMonth$" + mRent +
                ", Notes=" + mNotes +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Review temp = (Review) o;

        if(mType.compareTo(temp.mType) == 0)
        {
            if(mCompanyName.compareTo(temp.mCompanyName) == 0)
            {
                if(mAddress.compareTo(temp.mAddress) == 0)
                {
                    if (mCommunicationRating - temp.mCommunicationRating == 0)
                    {
                        if(mNeighborhoodRating - temp.mNeighborhoodRating == 0)
                        {
                            if (mConditionRating - temp.mConditionRating == 0)
                            {
                                if(mOverallRating - temp.mOverallRating == 0)
                                {
                                    if(mRent.compareTo(temp.mRent) == 0)
                                    {
                                        return mNotes.compareTo(temp.mNotes);
                                    }
                                    return mRent.compareTo(temp.mRent);
                                }
                                return (int) (mOverallRating - temp.mOverallRating);
                            }
                            return (int) (mConditionRating - temp.mConditionRating);
                        }
                        return  (int) (mNeighborhoodRating - temp.mNeighborhoodRating);
                    }
                    return (int) (mCommunicationRating - temp.mCommunicationRating);
                }
                return mAddress.compareTo(temp.mAddress);
            }
            return mCompanyName.compareTo(temp.mCompanyName);
        }
        return mType.compareTo(temp.mType);
    }
}
