package ir.zeroandone.app.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import java.util.Spliterator;
import java.util.function.Consumer;

public class VaadinUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {

    }

    @Override
    public void forEach(Consumer<? super Component> action) {

    }

    @Override
    public Spliterator<Component> spliterator() {
        return null;
    }
}
