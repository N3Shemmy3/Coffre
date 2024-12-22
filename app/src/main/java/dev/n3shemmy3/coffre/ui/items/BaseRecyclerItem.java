package dev.n3shemmy3.coffre.ui.items;

import java.util.ArrayList;

public abstract class BaseRecyclerItem<E> extends BaseItem {
    private ArrayList<E> items;

    public BaseRecyclerItem(ArrayList<E> items) {
        this.items = items;
    }

    public void setItems(ArrayList<E> items) {
        this.items = items;
    }

    public ArrayList<E> getItems() {
        return this.items;
    }
}
