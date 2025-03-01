package dev.n3shemmy3.coffre.backend.item;

import androidx.annotation.DrawableRes;

public class Category {
    private int id;
    private @DrawableRes int icon;
    private String name;

    public Category() {
    }

    public Category(int id, int icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;
        return getId() == category.getId() && getIcon() == category.getIcon() && getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIcon();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", icon=" + icon +
                ", name='" + name + '\'' +
                '}';
    }
}
