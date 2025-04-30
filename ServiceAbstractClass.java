public abstract class ServiceAbstractClass {
    protected int price;
    protected int lon;
    protected int lat;
    protected String name;
    protected String type;

    public ServiceAbstractClass(int lat, int lon, int price, String name, String type){
        this.lat = lat;
        this.lon = lon;
        this.price = price;
        this.name = name;
        this.type = type;
    }

}
