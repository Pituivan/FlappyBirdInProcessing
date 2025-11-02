import processing.core.*;

public class PipePair extends GameElement {
  // ----- Public Fields
  
  public final float offScreen;
  
  // ----- Private Fields
  
  private static final int SPACE_BETWEEN_PIPES = 100;
  private static final Vec2Int PIPE_CAP_SIZE = new Vec2Int(125, 50);
  private static final int PIPE_BODY_WIDTH = 60;
  
  private static final int OFFSET_BETWEEN_PIPES = 0;
  
  private static final float SPEED = 3f;
  
  // ----- Constructors
  
  public PipePair(PApplet app) {
    super(app);
    
    offScreen = app.width / 2f + PIPE_CAP_SIZE.x;
    
    app.rectMode(PApplet.CENTER);
  }
  
  // ----- Public Methods
  
  public void randomizeY() {
    float maxDistanceFromCenter = getApp().height / 2f - PIPE_CAP_SIZE.y * 4f;
    float randomY = getApp().random(-maxDistanceFromCenter, maxDistanceFromCenter);
    setPosition(new Vec2(getPosition().x, randomY));
  }
  
  public boolean collidesWithPlayer(Bird player) {
    Vec2 playerPos = player.getPosition();
    float radius = player.COLLIDER_RADIUS;

    // Upper pipe
    
    int capY = (int)(getPosition().y - SPACE_BETWEEN_PIPES);
    int bodyHeight = capY - PIPE_CAP_SIZE.y / 2;
    int bodyY = bodyHeight / 2;

    if (MathUtils.circleIntersectsRect(playerPos, radius,
                                       new Vec2(getPosition().x, capY),
                                       new Vec2(PIPE_CAP_SIZE.x, PIPE_CAP_SIZE.y))) 
      return true;

    if (MathUtils.circleIntersectsRect(playerPos, radius,
                                       new Vec2(getPosition().x, bodyY),
                                       new Vec2(PIPE_BODY_WIDTH, bodyHeight))) 
      return true;

    // Bottom pipe
    
    capY = (int)(getPosition().y + SPACE_BETWEEN_PIPES);
    bodyHeight = getApp().height - capY - PIPE_CAP_SIZE.y / 2;
    bodyY = getApp().height - bodyHeight / 2;

    if (MathUtils.circleIntersectsRect(playerPos, radius,
                                       new Vec2(getPosition().x, capY),
                                       new Vec2(PIPE_CAP_SIZE.x, PIPE_CAP_SIZE.y)))
      return true;

    if (MathUtils.circleIntersectsRect(playerPos, radius,
                                       new Vec2(getPosition().x, bodyY),
                                       new Vec2(PIPE_BODY_WIDTH, bodyHeight))) 
      return true;

    return false;
}
  
  // ----- Protected Methods
  
  @Override
  protected void update(float deltaTime) {
      Vec2 pos = getPosition();
      setPosition(new Vec2(pos.x - SPEED, pos.y));
      
      if (pos.x < -offScreen) {
        setPosition(new Vec2(offScreen + OFFSET_BETWEEN_PIPES, getPosition().y)); 
        randomizeY();
      }
  }
  
  @Override
  protected void render(Vec2Int screenPos) {
    PApplet app = getApp();
    app.strokeWeight(3);
    app.fill(70, 230, 20);
    
    // Upper pipe
    int capPosY = screenPos.y - SPACE_BETWEEN_PIPES;
    int bodyHeight = capPosY - PIPE_CAP_SIZE.y / 2;
    int bodyPosY = bodyHeight / 2;
    drawSinglePipe(screenPos.x, capPosY, bodyHeight, bodyPosY);
    
    // Bottom pipe
    capPosY = screenPos.y + SPACE_BETWEEN_PIPES;
    bodyHeight = app.width - capPosY - PIPE_CAP_SIZE.y / 2;
    bodyPosY = app.width - bodyHeight / 2;
    drawSinglePipe(screenPos.x, capPosY, bodyHeight, bodyPosY);
  }
  
  // ----- Private Methods
  
  private void drawSinglePipe(int x, int capPosY, int bodyHeight, int bodyPosY) {
    getApp().rect(x, capPosY, PIPE_CAP_SIZE.x, PIPE_CAP_SIZE.y);
    getApp().rect(x, bodyPosY, PIPE_BODY_WIDTH, bodyHeight);
  }
  
}
