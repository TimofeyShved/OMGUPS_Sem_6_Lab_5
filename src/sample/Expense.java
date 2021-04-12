package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Expense {
    //--------------------------------------------------------------------------------- переменные ------------------------------------------------------------
    StringProperty name = new SimpleStringProperty(""); // имя
    FloatProperty cost = new SimpleFloatProperty( 0 ) ; // цена

    StringProperty categoryOfExpenses = new SimpleStringProperty(""); // категория расходов

    // --------------------------------------------------------------------------------- обработка событый -------------------------------------------------------

    //----------------------------- ИМЯ
    public String getName () { // вернуть имя (ещё не использовали, но вдруг пригодиться)
        return name.get();
    }
    public StringProperty nameProperty () { // вернуть значение-строку с именем
        return name;
    }
    public void setName (String name) { // записать имя (ещё не использовали, но вдруг пригодиться)
        this.name.set(name);
    }
    //------------------------------ ЦЕНА
    public FloatProperty costProperty () { // вернуть значение-дробное с ценой
        return cost;
    }
    public FloatProperty getCost () { // вернуть имя (ещё не использовали, но вдруг пригодиться)
        return cost;
    }
    //----------------------------- КАТЕГОРИЯ
    public String getСategoryOfExpenses () { // вернуть категорию (ещё не использовали, но вдруг пригодиться)
        return categoryOfExpenses.get();
    }
    public StringProperty categoryOfExpensesProperty () { // вернуть значение-строку с категорией
        return categoryOfExpenses;
    }
    public void setСategoryOfExpenses (String name) { // записать категорию (ещё не использовали, но вдруг пригодиться)
        this.categoryOfExpenses.set(name);
    }



    //-------------------------------------------------------------------------------------конструтор -----------------------------------------------
    public Expense ( String name , Float cost, String categoryOfExpenses) {
        this.name = new SimpleStringProperty(name); // добавить имя в класс
        this.cost = new SimpleFloatProperty(cost); // добавить цену в класс
        this.categoryOfExpenses = new SimpleStringProperty(categoryOfExpenses); // добавить category в класс
    }
}
