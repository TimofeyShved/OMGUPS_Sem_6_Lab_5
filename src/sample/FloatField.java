package sample;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class FloatField extends TextField { // Создание кастомного(своего) текстового поля
    public FloatField(){ // конструктор
        super(); // вызывает конструктор родителя
        this.setText("0");// запись текста "0"
        this.setTextFormatter(new TextFormatter<String>( // изменение формата текста
                s->{
            if (s.getControlNewText().isEmpty()) // он пустой?
            return s;// да?! (」°ロ°)」 тогда верни его обратно! Немедленно!!!
            try {
                Float.parseFloat(s.getControlNewText());// а если всё хорошо, то распарси его во Float
                return s; // и верни уже её (＾▽＾)
            } catch(NumberFormatException e){ // обработка исключений (￢_￢;)
                return null;
            }
        }
) ) ;
    }
}
