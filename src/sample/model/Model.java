package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {
    private static final String BINARYFILE = "REVIEWS.dat";
    public static boolean binaryFileHasData(){
        File binaryFile = new File(BINARYFILE);
        return binaryFile.exists() && binaryFile.length() > 0;
    }

    public static ObservableList<Review> populateListFromBinaryFile(){
        ObservableList<Review> allReviews = FXCollections.observableArrayList();
        File binaryFile = new File(BINARYFILE);
        if(binaryFile.exists()){
            try{
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                Review[] temp = (Review[]) fileReader.readObject();
                allReviews.addAll(temp);
                fileReader.close();
            }
            catch(IOException | ClassNotFoundException e)
            {
                System.err.println("Error reading: " + e.getMessage());
            }
        }
        return allReviews;
    }

    public static boolean writeDataToBinaryFile(ObservableList<Review> allReviewList)
    {
        File binaryFile = new File(BINARYFILE);
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            Review[] temp = new Review[allReviewList.size()];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = allReviewList.get(i); }
            fileWriter.writeObject(temp);
            fileWriter.close();
        }
        catch (IOException e){
            System.err.println("Error writing: " + e.getMessage());
        }
        return false;
    }

}
