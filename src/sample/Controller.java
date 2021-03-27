package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    static Stage dialogStage;

    // -------------------------------------------------------------------- переменные ---------------------------------------------

    ObservableList<Expense> expenseList = FXCollections.observableArrayList();

    @FXML TableView<Expense> table;
    @FXML TableColumn<Expense, String> nameColumn;
    @FXML TableColumn<Expense, Number> costColumn;
    @FXML TableColumn<Expense, String> categoryColumn;
    @FXML Label sum;

    // ----------------------------------------------------------------------- инициализация ---------------------------------------
    public void init (ObservableList<Expense> expenseList){
        table.setItems(expenseList) ;

        // добавление строки в столбик (имя)
        nameColumn.setCellValueFactory(cellData ->
                cellData.getValue().nameProperty());

        // добавление строки в столбик (цена)
        costColumn.setCellValueFactory(cellData ->
                cellData.getValue().costProperty());

        table.setEditable(true);
        costColumn.setCellFactory(cellData ->
                new FloatCell (expenseList)
        );

        // подсчёт затрат
        table.setOnMouseClicked(event -> itogoUpdate());

        // добавление строки в столбик (цена)
        categoryColumn.setCellValueFactory(cellData ->
                cellData.getValue().categoryOfExpensesProperty()) ;
    }

    //--------------------------------------обновление результата! \(＾∀＾)/
    private void itogoUpdate(){
        FloatProperty sumCost= new SimpleFloatProperty(0f);
            for (Expense e: expenseList){ // перебор затрат
            sumCost.setValue(sumCost.getValue()+e.cost.getValue());
        }
        sum.setText(" Итого: "+sumCost.getValue()+" руб. "); // вывод затрат
    }

    // ----------------------------------------- пустая инициализация (если пользователь ничего не передал)
    public void init() {
        this.testInit();
        this.init(expenseList);
    }

    // ----------------------------------------- тестовая инициализация
    public void testInit() {
        expenseList.add(new Expense ( "Хлеб", new Float(30), "Еда"));
        expenseList.add ( new Expense ( "Вода", new Float(35 ), "Еда"));
        expenseList.add ( new Expense ( "Проезд", new Float(30 ), "Личное"));
        expenseList.add ( new Expense ( "Верёвка", new Float(150 ), "Хозяйство"));
        expenseList.add ( new Expense ( "Книга", new Float(340 ), "Личное"));
        expenseList.add ( new Expense ( "Мыло", new Float(120 ), "Хозяйство"));
        expenseList.add ( new Expense ( "Масло", new Float(35 ), "Еда"));
        expenseList.add ( new Expense ( "Сыр", new Float(220 ), "Еда"));
        expenseList.add ( new Expense ( "Табурет", new Float(1500 ), "Хозяйство"));
        //ObservableValue<Number> x; // данная строчка не имеет смысла, но может пригодится если переделать на возвращаемую функцию. Но у нас void =D
    }

    public void edit(MouseEvent mouseEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation (getClass().getResource("editWin.fxml"));
        Pane page = loader.load();
        dialogStage = new Stage() ;
        dialogStage.initModality( Modality.WINDOW_MODAL);

        Scene scene = new Scene(page) ;
        dialogStage.setScene(scene);

        EditWinController editWinController = loader.getController();
        int editingIndex = table.getSelectionModel().getFocusedIndex();
        editWinController.init(expenseList, editingIndex) ;
        dialogStage.showAndWait();
    }
}
