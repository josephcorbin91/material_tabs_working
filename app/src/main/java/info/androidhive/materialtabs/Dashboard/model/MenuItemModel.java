package info.androidhive.materialtabs.Dashboard.model;

/**
 * Created by yuva on 14/8/17.
 */

public class MenuItemModel {
    private int menu_title;
    private int menu_icon;

    public MenuItemModel(int menu_title, int menu_icon) {
        this.menu_title = menu_title;
        this.menu_icon = menu_icon;
    }

    public int getImageId() {
        return menu_icon;
    }

    public void setImageId(int menu_icon) {
        this.menu_icon = menu_icon;
    }

    public int getName() {
        return menu_title;
    }

    public void setName(int menu_title) {
        this.menu_title = menu_title;
    }
}
