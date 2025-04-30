public class Bound {
    public long maxLat;
    public long minLon;
    public long minLat;
    public long maxLon;

    public Bound(long maxLat,long minLon,long minLat,long maxLon) {
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.minLat = minLat;
        this.maxLon = maxLon;
    }

    // Check if a point (x, y) is inside the bound
    public boolean isInside(long x, long y) {
        return x >= maxLat && x <= minLat && y >= minLon && y <= maxLon;
    }
}
