package sample.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.controller.Controller;
import sample.model.Review;

import javax.security.auth.callback.LanguageCallback;
import java.sql.SQLOutput;

public class MainScene extends Scene {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 850;

    //combobox
    private ImageView reviewIV = new ImageView();
    private ComboBox<String> reviewTypeCB;
    private ComboBox<String> companyNameCB;
    private ComboBox<String> addressCB;
    private ComboBox<String> rentCB;
    private Label companyNameLabel = new Label("Company/Name:");

    //sliders
    private Slider communicationRatingSlider = new Slider(0.0, 5.0, 0.0);
    private Slider neighborhoodRatingSlider  = new Slider(0.0, 5.0, 0.0);
    private Slider conditionRatingSlider     = new Slider(0.0, 5.0, 0.0);
    private Slider overallRatingSlider       = new Slider(0.0, 5.0, 0.0);

    //List View
    private ListView<Review> reviewLV = new ListView<>();

    //label
    private Text art = new Text();

    String temp =
                    "██╗     ███████╗████████╗███████╗     ██████╗  ██████╗     ██╗  ██╗ ██████╗ ███╗   ███╗███████╗\n" +
                    "██║     ██╔════╝╚══██╔══╝██╔════╝    ██╔════╝ ██╔═══██╗    ██║  ██║██╔═══██╗████╗ ████║██╔════╝\n"+
                    "██║     █████╗     ██║   ███████╗    ██║  ███╗██║   ██║    ███████║██║   ██║██╔████╔██║█████╗  \n" +
                    "██║     ██╔══╝     ██║   ╚════██║    ██║   ██║██║   ██║    ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝  \n" +
                    "███████╗███████╗   ██║   ███████║    ╚██████╔╝╚██████╔╝    ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗\n" +
                    "╚══════╝╚══════╝   ╚═╝   ╚══════╝     ╚═════╝  ╚═════╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝" ;







    //Buttons
    private Button addButton = new Button("+ Add Review");
    private Button modifyButton = new Button("* Modify Review");

    private Controller controller = Controller.getInstance();
    private ObservableList<Review> reviewsList;
    private Review selectedReview;

    public MainScene()
    {
        //Size determine
        super(new GridPane(), WIDTH, HEIGHT);
        reviewsList = controller.getmAllReviews();
        reviewLV.setItems(reviewsList);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(10.0);
        pane.setPadding(new Insets(5));

        art.setText(temp);
        art.setFont(Font.font("monospace", 12));
        art.setFill(Color.SKYBLUE);
        art.setTextAlignment(TextAlignment.CENTER);
        pane.add(art, 0, 0, 3, 1);

        //reviewTypeCB.getItems().add("Private Landlord");
        //reviewTypeCB.getItems().add("Property Management Companies");
        //reviewTypeCB.getSelectionModel().selectedItemProperty().addListener((observableValue, review, t1) -> selectedReview(t1));

        //Combo boxes
        reviewTypeCB = new ComboBox<>(controller.getDistinctType());
        reviewTypeCB.setOnAction(e -> filter());
        pane.add(new Label("Renting type: "), 0, 1);
        pane.add(reviewTypeCB, 1, 1);

        companyNameCB = new ComboBox<>(controller.getDistinctCompanyName());
        companyNameCB.setOnAction(e -> filter());
        //pane.add(companyNameLabel, 0, 2);
        pane.add(new Label("Company/Name:"), 0, 2);
        pane.add(companyNameCB, 1, 2);

        addressCB = new ComboBox<>(controller.getDistinctAddress());
        addressCB.setOnAction(e -> filter());
        pane.add(new Label("Address: "), 0, 3);
        pane.add(addressCB, 1, 3);

        rentCB = new ComboBox<>(controller.getDistinctRent());
        rentCB.setOnAction(e -> filter());
        pane.add(new Label("Monthly Rent $"), 0, 4);
        pane.add(rentCB, 1, 4);

        //Communication rating
        pane.add(new Label("Communication Rating:"), 0, 5);
        communicationRatingSlider.setShowTickMarks(true);
        communicationRatingSlider.setShowTickLabels(true);
        communicationRatingSlider.setMajorTickUnit(1.0f);
        communicationRatingSlider.setMajorTickUnit(1.0f);
        pane.add(communicationRatingSlider, 1, 5);
        communicationRatingSlider.setOnMouseDragged(e -> filter());

        //neighborhoodRating
        pane.add(new Label("Neighborhood Rating:"), 0, 6);
        neighborhoodRatingSlider.setShowTickMarks(true);
        neighborhoodRatingSlider.setShowTickLabels(true);
        neighborhoodRatingSlider.setMajorTickUnit(1.0f);
        neighborhoodRatingSlider.setBlockIncrement(1.0f);
        pane.add(neighborhoodRatingSlider, 1, 6);
        neighborhoodRatingSlider.setOnMouseDragged(e -> filter());

        //conditionRating
        pane.add(new Label("Condition Rating:"), 0, 7);
        conditionRatingSlider.setShowTickMarks(true);
        conditionRatingSlider.setShowTickLabels(true);
        conditionRatingSlider.setMajorTickUnit(1.0f);
        conditionRatingSlider.setBlockIncrement(1.0f);
        pane.add(conditionRatingSlider, 1, 7);
        conditionRatingSlider.setOnMouseDragged(e -> filter());

        //overallRating
        pane.add(new Label("Overall Rating:"), 0, 8);
        overallRatingSlider.setShowTickMarks(true);
        overallRatingSlider.setShowTickLabels(true);
        overallRatingSlider.setMajorTickUnit(1.0f);
        overallRatingSlider.setBlockIncrement(1.0f);
        pane.add(overallRatingSlider, 1, 8);
        overallRatingSlider.setOnMouseDragged(e -> filter());

        //add button
        reviewLV.setPrefHeight(HEIGHT);
        reviewLV.setPrefWidth(WIDTH);

        reviewsList = controller.getmAllReviews();
        reviewLV.setItems(reviewsList);
        reviewLV.getSelectionModel().selectedItemProperty().addListener((observableValue, review, t1) -> selectedReview(t1));
        //addButton.setOnAction(event -> addInfluencer());

        modifyButton.setDisable(true);
        modifyButton.setOnAction(event -> modifyButton());
        addButton.setOnAction(event -> addButton());

        pane.add(addButton, 0, 10, 2, 1);
        pane.add(reviewLV, 0, 11, 2, 1);
        pane.add(modifyButton, 0, 12, 2, 1);
        this.setRoot(pane);
    }

    private void selectedReview(Review t1)
    {
        if(t1 != null)
        {
            if(t1.equals("Private Landlord"))
                companyNameLabel.setText("Landlords Name:");
            else
                companyNameLabel.setText("Management CO:");
            selectedReview = t1;
            //removeButton.setDisable(false);
            modifyButton.setDisable(selectedReview == null);
        }
    }

    private void selectedReview(String t1)
    {
        System.out.println(t1);
        if(t1 != null)
        {
            if(t1.equals("Private Landlord"))
                companyNameLabel.setText("Landlords Name:");
            else
                companyNameLabel.setText("Management CO:");
        }
    }

    private void modifyButton()
    {
        if(selectedReview == null)
            return;
        else
        {
            ViewNavigator.loadScene("Modify Review", new ModifyScene(this, selectedReview));
            updateDisplay();
        }
    }

    private void addButton()
    {
        System.out.println("testing");
        ViewNavigator.loadScene("Add Review", new AddScene(this));
        updateDisplay();
    }

    private void updateDisplay()
    {
        reviewLV.refresh();
        reviewTypeCB.setItems(controller.getDistinctType());
        companyNameCB.setItems(controller.getDistinctCompanyName());
        addressCB.setItems(controller.getDistinctAddress());
        rentCB.setItems(controller.getDistinctRent());
    }

    private void filter() {
        //type, companyName, address, communicationRating, neighborhoodRating, conditionRating, overallRating, rent, notes
        String notes = null;
        String type = reviewTypeCB.getSelectionModel().getSelectedItem();
        String companyName = companyNameCB.getSelectionModel().getSelectedItem();
        String address = addressCB.getSelectionModel().getSelectedItem();
        String rent = rentCB.getSelectionModel().getSelectedItem();

        double communicationRating = communicationRatingSlider.getValue();
        double neighborhoodRating  = neighborhoodRatingSlider.getValue();
        double conditionRating     = conditionRatingSlider.getValue();
        double overallRating       = overallRatingSlider.getValue();

        reviewsList = controller.filter(type, companyName, address, communicationRating, neighborhoodRating, conditionRating, overallRating, rent, notes);
        reviewLV.setItems(reviewsList);
    }
}
