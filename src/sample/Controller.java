package sample;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Controller {

    static Stage dialogStage;

    // -------------------------------------------------------------------- переменные ---------------------------------------------

    ObservableList<Expense> expenseList = FXCollections.observableArrayList();

    @FXML TableView<Expense> table;
    @FXML TableColumn<Expense, String> nameColumn;
    @FXML TableColumn<Expense, Number> costColumn;
    @FXML TableColumn<Expense, CategoryOfExpenses> categoryColumn;
    @FXML Label sum;

    // ----------------------------------------------------------------------- инициализация ---------------------------------------
    public void init (ObservableList<Expense> expenseList){
        table.setItems(expenseList) ;
        table.setEditable(true);

        // добавление строки в столбик (имя)
        nameColumn.setCellValueFactory(cellData ->
                cellData.getValue().nameProperty());

        // добавление строки в столбик (цена)
        costColumn.setCellValueFactory(cellData ->
                cellData.getValue().costProperty());

        costColumn.setCellFactory(cellData ->
                new FloatCell (expenseList)
        );

        // подсчёт затрат
        table.setOnMouseClicked(event -> itogoUpdate());

        // ================================================================== Category Of Expenses (COMBO BOX)

        ObservableList<CategoryOfExpenses> categoryList = FXCollections.observableArrayList( // лист с категориями (enum)
                CategoryOfExpenses.values());

        // добавление строки в столбик (Категория)
        categoryColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Expense, CategoryOfExpenses>, ObservableValue<CategoryOfExpenses>>() {

            @Override
            public ObservableValue<CategoryOfExpenses> call(TableColumn.CellDataFeatures<Expense, CategoryOfExpenses> param) {
                Expense name = param.getValue(); // передаём параметры поля
                // E,X,M
                String categoryCode = name.getСategoryOfExpenses(); // вытаскиваем код категории (строчка)
                CategoryOfExpenses category = CategoryOfExpenses.getByCode(categoryCode); // возвращает категорию по коду
                return new SimpleObjectProperty<CategoryOfExpenses>(category);
            }
        });

        // подгрузка ComboBox, для изменения данных
        categoryColumn.setCellFactory(ComboBoxTableCell.forTableColumn(categoryList));

        //запись выбранного поля ComboBox
        categoryColumn.setOnEditCommit((TableColumn.CellEditEvent<Expense, CategoryOfExpenses> event) -> {
            TablePosition<Expense, CategoryOfExpenses> pos = event.getTablePosition();

            CategoryOfExpenses category = event.getNewValue();

            int row = pos.getRow();
            Expense person = event.getTableView().getItems().get(row);

            person.setСategoryOfExpenses(category.getCode());
        });

        //categoryColumn.setMinWidth(120);
    }

    //--------------------------------------обновление результата! \(＾∀＾)/--------------------------------------------
    private void itogoUpdate(){
        FloatProperty sumCost= new SimpleFloatProperty(0f);
            for (Expense e: expenseList){ // перебор затрат
            sumCost.setValue(sumCost.getValue()+e.cost.getValue());
        }
        sum.setText(" Итого: "+sumCost.getValue()+" руб. "); // вывод затрат
    }

    // ----------------------------------------- пустая инициализация (если пользователь ничего не передал)----------------------
    public void init() {
        this.testInit();
        this.init(expenseList);
    }

    // ----------------------------------------- тестовая инициализация---------------------------------------------------------------
    public void testInit() {
        expenseList.add(new Expense ( "Хлеб", new Float(30), CategoryOfExpenses.EAT.getCode()));
        expenseList.add ( new Expense ( "Вода", new Float(35 ), CategoryOfExpenses.EAT.getCode()));
        expenseList.add ( new Expense ( "Проезд", new Float(30 ), CategoryOfExpenses.MAN.getCode()));
        expenseList.add ( new Expense ( "Верёвка", new Float(150 ), CategoryOfExpenses.XOZ.getCode()));
        expenseList.add ( new Expense ( "Книга", new Float(340 ), CategoryOfExpenses.MAN.getCode()));
        expenseList.add ( new Expense ( "Мыло", new Float(120 ), CategoryOfExpenses.XOZ.getCode()));
        expenseList.add ( new Expense ( "Масло", new Float(35 ), CategoryOfExpenses.EAT.getCode()));
        expenseList.add ( new Expense ( "Сыр", new Float(220 ), CategoryOfExpenses.EAT.getCode()));
        expenseList.add ( new Expense ( "Табурет", new Float(1500 ), CategoryOfExpenses.XOZ.getCode()));
        //ObservableValue<Number> x; // данная строчка не имеет смысла, но может пригодится если переделать на возвращаемую функцию. Но у нас void =D
    }

    // ------------------------------------------------- Изменения через кнопку ---------------------------------------------------------------
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
