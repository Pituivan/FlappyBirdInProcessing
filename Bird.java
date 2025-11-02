import processing.core.*;

public class Bird extends AnimatedSprite {
  // ----- Public Fields
  
  public final int COLLIDER_RADIUS = 20;
  
  // ----- Private Fields

  private static final String[] SPRITE_PATHS = new String[] { "Akari1.png", "Akari2.png", "Akari3.png", "Akari4.png" };
  private static final int[] ANIM_FRAME_SEQUENCE = new int[] { 0, 1, 2, 3, 2, 1 };
  private static final float[] ANIM_FRAME_DURATIONS = new float[] { .15f, .075f, .075f, .15f, .075f, .15f };

  private static final Vec2 COLLIDER_OFFSET = new Vec2(-5f, 0f);

  private final float GRAVITY = .35f;
  private final int DEFAULT_TILT = -5;

  private final float JUMP_FORCE = 6f;
  private final float DOUBLE_JUMP_FORCE = 8f;
  
  private final float BOUNCE_SPEED = 50f;
  private final float BOUNCE_SPEED_DECREASE_FACTOR = 1.05f;
  private final float BOUNCE_FALL_TILT_FACTOR_MULTIPLIER = 5f;

  private boolean renderCollider;
  private boolean gameOver;
  private boolean stopAnimating;

  private float fallTiltFactor = 1.075f;
  private Vec2 speed = Vec2.ZERO;
  
  // ----- Constructors

  public Bird(PApplet app) {
    super(app, loadSprites(app), ANIM_FRAME_SEQUENCE, ANIM_FRAME_DURATIONS);
  }

  // ----- Public Methods

  public void jump() {
    float speedY = speed.y < JUMP_FORCE ? JUMP_FORCE : DOUBLE_JUMP_FORCE;
    speed = new Vec2(0f, speedY);
  }
  
  public boolean isOffScreen() {
    return getPosition().y < -getApp().height / 2f - COLLIDER_RADIUS * 2f;
  }
  
  public final void renderCollider(boolean value) {
    renderCollider = value;
  }
  
  public final void gameOver() {
    gameOver = true;
  }

  // ----- Protected Methods

  @Override
  protected void update(float deltaTime) {
    if (!stopAnimating) {
      super.update(deltaTime);
    }
    
    if (gameOver) {
      speed = new Vec2(-BOUNCE_SPEED, 0f);
      fallTiltFactor *= -BOUNCE_FALL_TILT_FACTOR_MULTIPLIER;
      gameOver = false;
      stopAnimating = true;
    }

    speed = new Vec2(speed.x / BOUNCE_SPEED_DECREASE_FACTOR, speed.y - GRAVITY);

    Vec2 pos = getPosition();
    setPosition(new Vec2(pos.x, pos.y + speed.y));

    setRotation(PApplet.round(DEFAULT_TILT - speed.y * fallTiltFactor));
  }

  @Override
  protected void render(Vec2Int screenPos) {
    super.render(screenPos);
    if (!renderCollider) return;
    
    PApplet app = getApp();
    app.stroke(245, 15, 245);
    app.fill(245, 15, 245);
    app.ellipse(screenPos.x, screenPos.y, COLLIDER_RADIUS, COLLIDER_RADIUS);
  }

  // ----- Private Methods

  private static PImage[] loadSprites(PApplet app) {
    var sprites = new PImage[SPRITE_PATHS.length];
    for (int i = 0; i < SPRITE_PATHS.length; i++) {
      sprites[i] = app.loadImage(SPRITE_PATHS[i]);
    }

    return sprites;
  }
}
