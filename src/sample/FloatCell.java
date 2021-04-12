package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

public class FloatCell extends TableCell<Expense,Number> { // наследуем таблицу переменная / число
        FloatField number; // наше поле для числа
        ObservableList<Expense> expenseList;   // список переменных
        Expense expense; // наше поле для переменной

        // конструктор
        public FloatCell(ObservableList<Expense> expenseList )
        {
                this.expenseList = expenseList; // присваиваем список
        }

        @Override
        public void startEdit() { // начало редактирования
                if(!isEmpty()){
                        super.startEdit();
                        number=new FloatField(); // новое поле FloatField
                        expense=getTableView().getSelectionModel().getSelectedItem(); // выбранное поле
                        number.setText(expense.getCost().getValue().toString()); // вставляем наш текст
                        setGraphic(number); //отображаем число
                }
        }

        @Override
        public void cancelEdit() { // отмена редактирования
                super.cancelEdit();
                int index = expenseList.indexOf(expense); // узнаем индекс
                float newCost = Float.parseFloat(number.getText()); // переводим число в Float
                Expense newExpense = new Expense(expense.getName(), newCost, expense.categoryOfExpenses.getValue()); // подгружаем таблицу
                expenseList.set(index, newExpense); // меняем значение
                setGraphic(null); //не отображаем число
        }

        @Override
        public void updateItem(Number item, boolean empty) { // обновление поля
                super.updateItem (item, empty);
                if(empty) { // пустое?
                        setText(null); // пустое поле
                        setGraphic(null); ;
                } else
                if(isEditing()) // редаткируем?
                setGraphic(number); //отображаем число
                else {
                        setText(getItem().toString()) ;
                        setGraphic(null) ;
                }
        }
}
