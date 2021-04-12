package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class EditWinController {
    ObservableList<Expense> expenseList;
    int editingIndex ;
    @FXML TextField name;
    @FXML FloatField cost;
    @FXML ComboBox<String> category;
    @FXML Button ok;
    @FXML Button no;

    public void init (ObservableList<Expense > expenseList, int editingIndex ) {
        this.expenseList = expenseList;
        this.editingIndex = editingIndex;
        Expense expense = expenseList.get(editingIndex);
        name.setText(expense.getName());
        cost.setText((expense.getCost().getValue()).toString());

        ObservableList<CategoryOfExpenses> categoryList = FXCollections.observableArrayList( // лист с категориями (enum)
                CategoryOfExpenses.values());
        //category = new ComboBox<>(option);


        category.setValue(expense.categoryOfExpensesProperty().getValue());
        name.getParent().setOnMouseExited(e -> exit());
    }

    private void exit() {
        Expense expense = new Expense (
                name.getText(),
                Float.parseFloat(cost.getText()),
                category.getValue().toString());
        expenseList.set(editingIndex, expense) ;
    }
}
