package asule.primiers.bean;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerEntity extends BaseEntity{
    private String content;
    private int iconId;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public DrawerEntity(String content, int iconId) {
        this.content = content;
        this.iconId = iconId;
    }
}
