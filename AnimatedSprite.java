import processing.core.*;

public class AnimatedSprite extends RotatableGameElement {
  // ----- Private Fields
  
  private final PImage[] sprites;
  private final int[] animFrameSequence;
  private final float[] animDurations;;
  
  private int currentFrame;
  private float animProgression;
  
  // ----- Constructors
  
  public AnimatedSprite(PApplet app, PImage[] sprites, int[] animFrameSequence, float[] animDurations) {
    super(app);
    
    if (animFrameSequence.length != animDurations.length) {
      throw new IllegalArgumentException("animFrameSequence and animDurations arrays must have the same length!");
    }
    
    for (int frameIndex : animFrameSequence) {
      if (frameIndex < 0 || frameIndex >= sprites.length) {
        throw new IllegalArgumentException("Some index from animFrameSequence is out of bounds!");
      }
    }
    
    this.sprites = sprites;
    this.animFrameSequence = animFrameSequence;
    this.animDurations = animDurations;
    
    getApp().imageMode(PApplet.CENTER);
  }
  
  // ----- Protected Methods
  
  @Override
  protected void update(float deltaTime) {
    animProgression += deltaTime;
    
    if (animProgression >= animDurations[currentFrame]) {
      animProgression = 0f;
      currentFrame = (currentFrame + 1) % sprites.length;
    }
  }
  
  @Override
  protected void render(Vec2Int screenPos) {
    PImage sprite = sprites[currentFrame];
    Vec2 scale = getScale();
    
    //var pos = Vec2Int.round(
    //  screenPos.x - sprite.width / 2 * scale.x,
    //  screenPos.y - sprite.height / 2 * scale.y
    //  );
    var size = Vec2Int.round(
      sprite.width * scale.x,
      sprite.height * scale.y
    );
    
    PApplet app = getApp();
    app.pushMatrix();
    app.translate(screenPos.x, screenPos.y);
    app.rotate(getRotation() * PApplet.PI / 180f);
    app.image(sprite, 0, 0, size.x, size.y);
    app.popMatrix();
  }
}
