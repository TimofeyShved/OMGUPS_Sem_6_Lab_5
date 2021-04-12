package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

public enum CategoryOfExpenses {
   EAT("E", "Еда"), XOZ("X", "Хозяйство"), MAN("M", "Личное"),;

    private String code;
    private String text;

    private CategoryOfExpenses(String code, String text) { // конструктор
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    } // вернуть код

    public String getText() {
        return text;
    } // вернуть текст

    public static CategoryOfExpenses getByCode(String categoryCode) { // возвращает категорию по коду
        for (CategoryOfExpenses g : CategoryOfExpenses.values()) {
            if (g.code.equals(categoryCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    } // вернуть текст
}
