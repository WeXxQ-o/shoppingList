package com.example.shoppinglist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private ListView<Item> listView;
    @FXML private TextField nameField;
    @FXML private Spinner<Integer> quantitySpinner;
    @FXML private Label countLabel;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        listView.setItems(items);

        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1));

        listView.setOnMouseClicked(e -> {
            Item selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                nameField.setText(selected.getName());
                quantitySpinner.getValueFactory().setValue(selected.getQuantity());
            }
        });

        updateCount();
    }

    @FXML
    protected void onAddClick() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) return;

        Item newItem = new Item(name, quantitySpinner.getValue());
        items.add(newItem);

        clearInputs();
        updateCount();
    }

    @FXML
    protected void onEditClick() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String name = nameField.getText().trim();

        if (selectedIndex == -1 || name.isEmpty()) return;

        Item item = items.get(selectedIndex);
        item.setName(name);
        item.setQuantity(quantitySpinner.getValue());

        items.set(selectedIndex, item);
        clearInputs();
    }

    @FXML
    protected void onDeleteClick() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            items.remove(selectedIndex);
            clearInputs();
            updateCount();
        }
    }

    private void clearInputs() {
        nameField.clear();
        quantitySpinner.getValueFactory().setValue(1);
        listView.getSelectionModel().clearSelection();
    }

    private void updateCount() {
        countLabel.setText("Celkový počet položiek v zozname: " + items.size());
    }
}