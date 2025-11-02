import processing.core.*;

public abstract class GameElement {
  // ----- Private Fields
  
  private final PApplet app;
  
  private Vec2 worldPos = Vec2.ZERO;
  private Vec2 scale = Vec2.ONE;
  
  // ----- Property Getters & Setters
  
  public final Vec2 getPosition() {
    return worldPos;
  }
  
  public final void setPosition(Vec2 value) {
    worldPos = value;
  }
  
  public final Vec2 getScale() {
    return scale;
  }
  
  public final void setScale(Vec2 value) {
    scale = value;
  }
  
  protected final PApplet getApp() {
    return app;
  }
  
  // ----- Constructors
  
  public GameElement(PApplet app) {
    this.app = app;
  }
  
  // ----- Public Methods
  
  public final void render() {
    var screenPos = Vec2Int.round(
      app.width / 2 + worldPos.x,
      app.height / 2 - worldPos.y
    );
      
    render(screenPos);
  }
  
  public final void update() {
    float deltaTime = 1f / app.frameRate;
    update(deltaTime);
  }
  
  // ----- Protected Methods
  
  protected abstract void render(Vec2Int screenPos);
  protected abstract void update(float deltaTime);
}
