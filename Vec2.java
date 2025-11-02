public class Vec2 {
  // ----- Public Fields
  
  public static final Vec2 ZERO = new Vec2(0, 0);
  public static final Vec2 ONE = new Vec2(1f, 1f);
  
  public final float x;
  public final float y;
  
  // ----- Constructors
   
  public Vec2(float x, float y) {
    this.x = x;
    this.y = y;
  }
}
