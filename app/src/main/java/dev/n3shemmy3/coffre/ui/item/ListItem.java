package dev.n3shemmy3.coffre.ui.item;

import androidx.annotation.NonNull;

public class ListItem extends BaseItem {

    private String icon;
    private String title;
    private String subTitle;

    public ListItem(int id, String icon, String title) {
        super(id);
        this.icon = icon;
        this.title = title;
        this.subTitle = null;
    }

    public ListItem(int id, String icon, String title, String subTitle) {
        super(id);
        this.icon = icon;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + getIcon() + getTitle() + getSubTitle();
    }
}
