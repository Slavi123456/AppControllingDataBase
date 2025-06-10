package myApp.utils.dataTypes;

public class DataBase {
    private String name;
    private String img_path;

    public DataBase(String img_path, String name) {
        setImg_path(img_path);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            this.name = "";
            return;
        }
        this.name = name;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        if (img_path == null) { throw new NullPointerException("Image path is null"); }
        this.img_path = img_path;
    }

    @Override
    public String toString() {
        return String.format("%s [Name: %s]",
                this.getClass().getSimpleName(),
                this.name);
    }
}
