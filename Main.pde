static final int SPACE_BETWEEN_PIPES = 500;

final ArrayList<GameElement> gameElements = new ArrayList<GameElement>();
boolean gameOver;

Bird player;
PipePair[] pipes;

void setup() {
  size(750, 750);
  noSmooth();
  
  player = new Bird(this);
  player.setPosition(new Vec2(-175, 0));
  player.setScale(new Vec2(3f, 3f));
  //player.renderCollider(true);
  
  pipes = new PipePair[2];
  for (int i = 0; i < pipes.length; i++) {
    var pipe = new PipePair(this);
    pipe.setPosition(new Vec2(pipe.offScreen + i * SPACE_BETWEEN_PIPES, 0));
    pipe.randomizeY();
    
    pipes[i] = pipe;
  }

  for (PipePair pipe : pipes) {
    gameElements.add(pipe);
  }
  gameElements.add(player);
}

void draw() {
  background(15, 158, 245);
  
  for (GameElement gameObj : gameElements) {
    gameObj.render();
  }
  
  if (gameOver) {
    player.update();
    return;
  }
  
  for (GameElement gameObj : gameElements) {
    gameObj.update();
  }
  
  if (player.isOffScreen()) {
    gameOver = true;
    return;
  }
  
  for (PipePair pipe : pipes) {
    if (pipe.collidesWithPlayer(player)) {
      gameOver = true;
      player.gameOver();
      return;
    }
  }
}

void keyPressed() {
  if (gameOver) return;
  
  if (key == ' ') {
    player.jump();
  }
}

void mouseClicked() {
  if (gameOver) return;
  
  player.jump();
}
