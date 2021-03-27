package sample;

import javafx.beans.InvalidationListener;

public interface Observable {
    void addListener(InvalidationListener listener);
    void removeListener(InvalidationListener listener);
}
