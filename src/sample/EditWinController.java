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
    @FXML TextField cost;
    @FXML TextField category;

    public void init (ObservableList<Expense > expenseList, int editingIndex ) {
        this.expenseList = expenseList;
        this.editingIndex = editingIndex;
        Expense expense = expenseList.get(editingIndex);
        name.setText(expense.getName());
        cost.setText((expense.getCost().getValue()).toString());
        category.setText(expense.getСategoryOfExpenses());
        name.getParent().setOnMouseExited(e -> exit()) ;
    }

    private void exit() {
        Expense expense = new Expense (
                name.getText(),
                Float.parseFloat(cost.getText()),
                category.getText());
        expenseList.set(editingIndex, expense) ;
    }

    public void save(MouseEvent mouseEvent) {

    }

    public void cancel(MouseEvent mouseEvent) {

    }
}
