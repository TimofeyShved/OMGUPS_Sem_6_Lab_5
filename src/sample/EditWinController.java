package sample;

import javafx.beans.property.StringProperty;
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
    @FXML ComboBox category;
    @FXML Button ok;
    @FXML Button no;

    public void init (ObservableList<Expense > expenseList, int editingIndex ) {
        this.expenseList = expenseList;
        this.editingIndex = editingIndex;
        Expense expense = expenseList.get(editingIndex); // подгрузка старых значений и выборка изменяемых
        name.setText(expense.getName());
        cost.setText((expense.getCost().getValue()).toString());

        ObservableList<CategoryOfExpenses> categoryList = FXCollections.observableArrayList( // лист с категориями (enum)
                CategoryOfExpenses.values());
        category.setItems(categoryList); // записываем наш список внутрь ComboBox

        String categoryCode = expense.getСategoryOfExpenses(); // вытаскиваем код категории (строчка)
        CategoryOfExpenses categoryOfExpenses = CategoryOfExpenses.getByCode(categoryCode); // возвращает категорию по коду
        category.setValue(categoryOfExpenses); // выбранное значение ComboBox

        // если сделалили действие
        name.getParent().setOnMouseExited(e -> exit());
        cost.getParent().setOnMouseExited(e -> exit());
        category.getParent().setOnMouseExited(e -> exit());
    }

    private void exit() {
        CategoryOfExpenses categoryOfExpenses = CategoryOfExpenses.getByName(category.getValue().toString()); // возвращает категорию по названию

        Expense expense = new Expense ( // запись новых значений
                name.getText(),
                Float.parseFloat(cost.getText()),
                categoryOfExpenses.getCode());
        expenseList.set(editingIndex, expense) ;
    }
}
