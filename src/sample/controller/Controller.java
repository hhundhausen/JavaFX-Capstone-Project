package sample.controller;
import javafx.collections.FXCollections;
import sample.model.Model;
import sample.model.Review;
import javafx.collections.ObservableList;
import java.text.NumberFormat;

public class Controller {
    private static Controller theInstance;
    private ObservableList<Review> mAllReviewsList;
    private ObservableList<Review> mFilteredReviewsList;
    private Controller(){}

    public static Controller getInstance()
    {
        if(theInstance == null){
            theInstance = new Controller();
            if (Model.binaryFileHasData())
                theInstance.mAllReviewsList = Model.populateListFromBinaryFile();
            else
                theInstance.mAllReviewsList = FXCollections.observableArrayList();

            theInstance.mFilteredReviewsList = FXCollections.observableArrayList();
        }
        return theInstance;
    }

    public ObservableList<Review> getmAllReviews(){
        return mAllReviewsList;
    }
    public ObservableList<String> getDistinctType()
    {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll(" ", "Private Landlord", "Property Management Companies");
        String type;
        for(Review r: mAllReviewsList)
        {
            type = r.getmType().trim();
            if(!type.isEmpty() && !typeList.contains(type))
                typeList.add(type);
        }
        FXCollections.sort(typeList);
        return typeList;
    }

    public ObservableList<String> getDistinctCompanyName()
    {
        ObservableList<String> companyNameList= FXCollections.observableArrayList();
        companyNameList.add(" ");
        String companyName;
        for(Review r: mAllReviewsList)
        {
            companyName = r.getmCompanyName().trim();
            if(!companyName.isEmpty() && !companyNameList.contains(companyName))
                companyNameList.add(companyName);
        }
        FXCollections.sort(companyNameList);
        return companyNameList;
    }

    public ObservableList<String> getDistinctAddress()
    {
        ObservableList<String> addressList= FXCollections.observableArrayList();
        addressList.add(" ");
        String address;
        for(Review r: mAllReviewsList)
        {
            address = r.getmAddress().trim();
            if(!address.isEmpty() && !addressList.contains(address))
                addressList.add(address);
        }
        FXCollections.sort(addressList);
        return addressList;
    }

    public ObservableList<String> getDistinctRent()
    {
        NumberFormat currecny = NumberFormat.getInstance();
        ObservableList<String> rentList= FXCollections.observableArrayList();
        rentList.add(" ");
        String rent;
        for(Review r: mAllReviewsList)
        {
            rent = r.getmRent().trim();
            if(!rent.isEmpty() && !rentList.contains(rent))
                rentList.add(rent);
        }
        FXCollections.sort(rentList);
        return rentList;
    }

    public ObservableList<Review> filter(String type, String companyName, String address, double communicationRating,
                                         double neighborhoodRating, double conditionRating, double overallRating, String rent, String notes)
    {
        mFilteredReviewsList.clear();
        boolean meetsCriteria;
        for(Review r: mAllReviewsList)
        {
            meetsCriteria = true;
            if(type != null && !type.equalsIgnoreCase(" ") && !type.equalsIgnoreCase(r.getmType()))
                        meetsCriteria = false;

            if(companyName != null && !companyName.equalsIgnoreCase(" ") && !companyName.equalsIgnoreCase(r.getmCompanyName()))
                    meetsCriteria = false;

            if(address != null && !address.equalsIgnoreCase(" ") && !address.equalsIgnoreCase(r.getmAddress()))
                    meetsCriteria = false;

            if(r.getmCommunicationRating() < communicationRating)
                meetsCriteria = false;
            if(r.getmNeighborhoodRating() < neighborhoodRating)
                meetsCriteria = false;
            if(r.getmConditionRating() < conditionRating)
                meetsCriteria = false;
            if (r.getmOverallRating() < overallRating)
                meetsCriteria = false;

            if(rent != null && !rent.equalsIgnoreCase(" ") && !rent.equalsIgnoreCase(r.getmRent()))
                    meetsCriteria = false;

            if (meetsCriteria)
                mFilteredReviewsList.add(r);
        }
        return mFilteredReviewsList;
    }
    public void savaData()
    {
        Model.writeDataToBinaryFile(mAllReviewsList);
    }
}
