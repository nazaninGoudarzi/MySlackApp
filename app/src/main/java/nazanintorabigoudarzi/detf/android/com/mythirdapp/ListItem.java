package nazanintorabigoudarzi.detf.android.com.mythirdapp;

/**
 * Created by nazanin on 8/22/2017.
 */

public class ListItem {
    private String title;
    private String tag;
    private String image;
    private Boolean icon;

    public ListItem(String title, String tag, String image, Boolean icon) {
        this.title = title;
        this.tag = tag;
        this.image = image;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getImage() {
        return image;
    }

    public boolean getIcon() {
        return icon;
    }
}
