public class MathUtils {
  public static boolean circleIntersectsRect(Vec2 circleCenter, float radius, Vec2 rectPos, Vec2 rectSize) {
    float dx = Math.abs(circleCenter.x - rectPos.x);
    float dy = Math.abs(circleCenter.y - rectPos.y);

    if (dx > radius + rectSize.x / 2f) return false;
    if (dy > radius + rectSize.y / 2f) return false;

    if (dx <= rectSize.x / 2f) return true;
    if (dy <= rectSize.y / 2f) return true;

    float cornerDistSq = (dx - rectSize.x / 2f) * (dx - rectSize.y / 2f) + (dy - rectSize.x / 2f) * (dy - rectSize.y / 2f);
    return cornerDistSq <= radius*radius;
}
}
