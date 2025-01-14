
public class MyPrettyRectangle {
	
	private double x1, y1, x2, y2;

	public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
	}

	public boolean contains(MyPrettyRectangle other) {
		return this.getX1() <= other.getX1() && this.getY1() <= other.getY1() && this.getX2() >= other.getX2() && this.getY2() >= other.getY2();
	}

	public MyPoint getCenter() {
		 double centerX = (getX1() + getX2()) / 2.0;
	     double centerY = (getY1() + getY2()) / 2.0;
	     return new MyPoint(centerX, centerY);
	}

	public double getArea() {
		return (getX2() - getX1()) * (getY2() - getY1());
	}

	public double getPerimeter() {
		 return 2 * ((getX2() - getX1()) + (getY2() - getY1()));
	}

	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (!(obj instanceof MyPrettyRectangle)) return false;
	        MyPrettyRectangle other = (MyPrettyRectangle) obj;
	        return Double.compare(getX1(), other.getX1()) == 0 &&
	               Double.compare(getY1(), other.getY1()) == 0 &&
	               Double.compare(getX2(), other.getX2()) == 0 &&
	               Double.compare(getY2(), other.getY2()) == 0;
	    }

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}
}
