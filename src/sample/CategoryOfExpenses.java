package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

public enum CategoryOfExpenses {
   FEMALE("F", "Famale"), MALE("M", "Male");

    private String code;
    private String text;

    private CategoryOfExpenses(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static CategoryOfExpenses getByCode(String genderCode) {
        for (CategoryOfExpenses g : CategoryOfExpenses.values()) {
            if (g.code.equals(genderCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
