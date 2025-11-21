import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class MyWorld extends World
{
    private int score = 0;
    private int lives = 3;
    private int spawnDelay = 60;
    private int spawnCounter = 0;
    private int level = 1;
    private int fruitsToNextLevel = 10;
    private int fruitsCut = 0;
    
    private ScoreBoard scoreBoard;
    private LifeDisplay lifeDisplay;
    
    private boolean gameIsOver = false;
    private int cleanupCounter = 0; // TAMBAHAN: Counter untuk cleanup berkala
    
    public MyWorld()
    {    
        super(800, 600, 1);
        prepare();
    }
    
    private void prepare()
    {
        GreenfootImage bg = new GreenfootImage(800, 600);
        bg.setColor(new Color(135, 206, 235));
        bg.fill();
        setBackground(bg);
        
        scoreBoard = new ScoreBoard();
        addObject(scoreBoard, 100, 30);
        
        lifeDisplay = new LifeDisplay();
        addObject(lifeDisplay, 700, 30);
        
        Sword sword = new Sword();
        addObject(sword, 400, 300);
    }
    
    public void act()
    {
        if(!gameIsOver)
        {
            spawnFruits();
            updateUI();
            checkLevelUp();
            
            // PERBAIKAN: Cleanup lebih sering (setiap 10 acts)
            cleanupCounter++;
            if(cleanupCounter >= 10)
            {
                cleanupCounter = 0;
                cleanupOffscreenObjects();
            }
        }
    }
    
    private void spawnFruits()
    {
        spawnCounter++;
        if(spawnCounter >= spawnDelay)
        {
            spawnCounter = 0;
            
            int random = Greenfoot.getRandomNumber(100);
            
            if(random < 15)
            {
                Bomb bomb = new Bomb();
                int x = Greenfoot.getRandomNumber(700) + 50;
                addObject(bomb, x, 620);
            }
            else
            {
                Fruit fruit = createRandomFruit();
                int x = Greenfoot.getRandomNumber(700) + 50;
                addObject(fruit, x, 620);
            }
            
            spawnDelay = Math.max(30, 60 - (level * 3));
        }
    }
    
    // PERBAIKAN: Cleanup lebih agresif
    private void cleanupOffscreenObjects()
    {
        // Hapus buah yang keluar layar atau di posisi aneh
        List<Fruit> fruits = new ArrayList<Fruit>(getObjects(Fruit.class));
        for(Fruit fruit : fruits)
        {
            if(fruit.getWorld() != null)
            {
                int x = fruit.getX();
                int y = fruit.getY();
                
                // Hapus jika di bawah layar, di atas, atau di samping
                if(y > 610 || y < -50 || x < -50 || x > 850)
                {
                    removeObject(fruit);
                }
            }
        }
        
        // Hapus bom yang keluar layar
        List<Bomb> bombs = new ArrayList<Bomb>(getObjects(Bomb.class));
        for(Bomb bomb : bombs)
        {
            if(bomb.getWorld() != null)
            {
                int x = bomb.getX();
                int y = bomb.getY();
                
                if(y > 610 || y < -50 || x < -50 || x > 850)
                {
                    removeObject(bomb);
                }
            }
        }
        
        // TAMBAHAN: Hapus FruitHalf yang stuck
        List<FruitHalf> halves = new ArrayList<FruitHalf>(getObjects(FruitHalf.class));
        for(FruitHalf half : halves)
        {
            if(half.getWorld() != null)
            {
                int y = half.getY();
                if(y > 650 || y < -100)
                {
                    removeObject(half);
                }
            }
        }
        
        // TAMBAHAN: Hapus Particle yang stuck
        List<Particle> particles = new ArrayList<Particle>(getObjects(Particle.class));
        for(Particle particle : particles)
        {
            if(particle.getWorld() != null)
            {
                int y = particle.getY();
                if(y > 650 || y < -100)
                {
                    removeObject(particle);
                }
            }
        }
    }
    
    private Fruit createRandomFruit()
    {
        int type = Greenfoot.getRandomNumber(5);
        switch(type)
        {
            case 0: return new Apple();
            case 1: return new Orange();
            case 2: return new Watermelon();
            case 3: return new Banana();
            default: return new Strawberry();
        }
    }
    
    public void addScore(int points)
    {
        score += points;
        fruitsCut++;
    }
    
    public void loseLife()
    {
        lives--;
        if(lives <= 0 && !gameIsOver)
        {
            gameOver();
        }
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public boolean isGameOver()
    {
        return gameIsOver;
    }
    
    private void updateUI()
    {
        scoreBoard.updateScore(score, level);
        lifeDisplay.updateLives(lives);
    }
    
    private void checkLevelUp()
    {
        if(fruitsCut >= fruitsToNextLevel)
        {
            level++;
            fruitsCut = 0;
            fruitsToNextLevel += 5;
            showLevelUpMessage();
        }
    }
    
    private void showLevelUpMessage()
    {
        LevelUpText levelText = new LevelUpText(level);
        addObject(levelText, 400, 300);
    }
    
    private void gameOver()
    {
        gameIsOver = true;
        removeAllFruits();
        
        GameOver gameOverScreen = new GameOver(score);
        addObject(gameOverScreen, 400, 300);
    }
    
    private void removeAllFruits()
    {
        List<Fruit> fruits = new ArrayList<Fruit>(getObjects(Fruit.class));
        for(Fruit fruit : fruits)
        {
            if(fruit.getWorld() != null)
            {
                removeObject(fruit);
            }
        }
        
        List<Bomb> bombs = new ArrayList<Bomb>(getObjects(Bomb.class));
        for(Bomb bomb : bombs)
        {
            if(bomb.getWorld() != null)
            {
                removeObject(bomb);
            }
        }
        
        List<FruitHalf> halves = new ArrayList<FruitHalf>(getObjects(FruitHalf.class));
        for(FruitHalf half : halves)
        {
            if(half.getWorld() != null)
            {
                removeObject(half);
            }
        }
        
        List<Particle> particles = new ArrayList<Particle>(getObjects(Particle.class));
        for(Particle particle : particles)
        {
            if(particle.getWorld() != null)
            {
                removeObject(particle);
            }
        }
        
        List<Explosion> explosions = new ArrayList<Explosion>(getObjects(Explosion.class));
        for(Explosion explosion : explosions)
        {
            if(explosion.getWorld() != null)
            {
                removeObject(explosion);
            }
        }
        
        List<SlashEffect> slashes = new ArrayList<SlashEffect>(getObjects(SlashEffect.class));
        for(SlashEffect slash : slashes)
        {
            if(slash.getWorld() != null)
            {
                removeObject(slash);
            }
        }
    }
}