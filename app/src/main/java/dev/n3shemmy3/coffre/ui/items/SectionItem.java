package dev.n3shemmy3.coffre.ui.items;

import java.util.ArrayList;

public class SectionItem extends RecyclerItem {

    private String title;
    private String actionText;


    public SectionItem(String title, String actionText, ArrayList<BaseItem> items) {
        super(items);
        this.title = title;
        this.actionText = actionText;
    }

    public String getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
