public class BoundManager {
    private static Bound currentBound = null;

    public static void setBound(Bound bound) {
        currentBound = bound;
    }

    public static Bound getBound() {
        return currentBound;
    }

    public static boolean isDefined() {
        return currentBound != null;
    }

    public static void clear() {
        currentBound = null;
    }
}