module com.example.shoppinglist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shoppinglist to javafx.fxml;
    exports com.example.shoppinglist;
}