package org.states;

public interface FieldState {
    void handleInput(WebElement field, String text);
    FieldState clearField();
    FieldState inputText(String text);
}
