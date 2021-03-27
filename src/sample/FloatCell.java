package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

public class FloatCell extends TableCell<Expense,Number> {
        FloatField number ;
        ObservableList<Expense> expenseList;
        Expense expense;

        public FloatCell(ObservableList<Expense > expenseList )
        { this.expenseList = expenseList ;
        }

        @Override
        public void startEdit() {
                if(!isEmpty()){
                        super.startEdit();
                        number=new FloatField();
                        expense=getTableView().getSelectionModel().getSelectedItem();
                        number.setText(expense.getCost().getValue().toString());
                        setGraphic(number);
                }
        }

        @Override
        public void cancelEdit ( ) {
                super.cancelEdit();
                int index = expenseList.indexOf(expense) ;
                float newCost = Float.parseFloat(number.getText()) ;
                Expense newExpense = new Expense(expense.getName(), newCost, expense.categoryOfExpenses.getValue());
                expenseList.set(index, newExpense) ;
                setGraphic(null) ;
        }

        @Override
        public void updateItem (Number item , boolean empty) {
                super.updateItem (item, empty);
                if(empty) {
                        setText(null);
                        setGraphic(null); ;
                } else
                if(isEditing())
                setGraphic(number);
                else {
                        setText(getItem().toString()) ;
                        setGraphic(null) ;
                }
        }
}
