import processing.core.*;

public abstract class RotatableGameElement extends GameElement {
  // ----- Private Fields
  
  private int rotation;
  
  // ----- Property Getter & Setters
  
  public final int getRotation() {
    return rotation;
  }
  
  public final void setRotation(int value) {
    rotation = value;
  }
  
  // ----- Constructors
  
  public RotatableGameElement(PApplet app) {
    super(app);
  }
}
