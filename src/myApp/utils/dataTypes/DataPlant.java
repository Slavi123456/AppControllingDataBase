package myApp.utils.dataTypes;

public class DataPlant extends DataBase {
    private String order;
    private String family;
    private String genus;

    public DataPlant(String name, String img_path) {
        super(name, img_path);
        order = "";
        family = "";
        genus = "";
    }
    public DataPlant(String name, String img_path, String order, String genus, String family) {
        super(name, img_path);
        setOrder(order);
        setFamily(family);
        setGenus(genus);
    }

    public String getOrder() {
        return order;
    }
    public String getGenus() {
        return genus;
    }
    public String getFamily() {
        return family;
    }

    public void setOrder(String order) {
        if(order == null) {
            this.order = "";
            return;
        }
        this.order = order;
    }
    public void setFamily(String family) {
        if(family == null) {
            this.family = "";
            return;
        }
        this.family = family;
    }
    public void setGenus(String genus) {
        if(genus == null) {
            this.genus = "";
            return;
        }
        this.genus = genus;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\n  Plant Details: Order: %s, Genus: %s, Family: %s",
                this.order,
                this.genus,
                this.family);
    }
}
