
public class MyPoint {

	private final double x, y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyPoint)) return false;
        MyPoint other = (MyPoint) obj;
        return Double.compare(x, other.x) == 0 &&
               Double.compare(y, other.y) == 0;
    }

}
