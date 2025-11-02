import processing.core.*;

public class Vec2Int {
  // ----- Public Fields
  
  public final int x;
  public final int y;
  
  // ----- Constructors
  
  public Vec2Int(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  // ----- Public Methods
  
  public static Vec2Int round(float x, float y) {
    return new Vec2Int(PApplet.round(x), PApplet.round(y));
  }
}
