
public class BoundingBoxFactory {
    
	public static MyPrettyRectangle getBoundingBox(MyPrettyRectangle[] rectangles) {
        if (rectangles == null) {
            return null;
        }
        if(rectangles.length == 0) {
        	return new MyPrettyRectangle(0, 0, 0, 0);
        }

        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;

        for (MyPrettyRectangle rect : rectangles) {
            if (rect != null) {
                minX = Math.min(minX, rect.getX1());
                minY = Math.min(minY, rect.getY1());
                maxX = Math.max(maxX, rect.getX2());
                maxY = Math.max(maxY, rect.getY2());
            }
        }

        if (minX == Double.MAX_VALUE || minY == Double.MAX_VALUE || maxX == Double.MIN_VALUE || maxY == Double.MIN_VALUE) {
            return new MyPrettyRectangle(0, 0, 0, 0);
        }

        return new MyPrettyRectangle(minX, minY, maxX, maxY);
    }
}

