package dev.n3shemmy3.coffre.ui.item;

import androidx.annotation.NonNull;

public class ListItem extends BaseItem {

    private String icon;
    private String title;
    private String subTitle;

    private String endText;
    private String endIcon;

    public ListItem(int id, String icon, String title) {
        super(id);
        this.icon = icon;
        this.title = title;
    }

    public ListItem(int id, String icon, String title, String subTitle) {
        super(id);
        this.icon = icon;
        this.title = title;
        this.subTitle = subTitle;
    }

    public ListItem(int id, String icon, String title, String subTitle, String endText, String endIcon) {
        super(id);
        this.icon = icon;
        this.title = title;
        this.subTitle = subTitle;
        this.endText = endText;
        this.endIcon = endIcon;
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

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    public String getEndIcon() {
        return endIcon;
    }

    public void setEndIcon(String endIcon) {
        this.endIcon = endIcon;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + getIcon() + getTitle() + getSubTitle() + getEndIcon() + getEndText();
    }
}
