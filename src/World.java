import org.newdawn.slick.Color;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class World
{
//    private static final int WINDOW_WIDTH = 700;
//    private static final int WINDOW_HEIGHT = 700;
//    private static int FPS = 60;
    private Organism[][] organisms;
    private CopyOnWriteArrayList<Organism> present;
    private int[] doodlePop;
    private int[] antPop;
    private int timeStep;
    private int width;
    private int height;
    private int doodleMutation;
    private int antMutation;
//    private BufferedImage backBuffer;
//    private Insets insets;

    public World()
    {
        super();
        width = 100;
        height = 100;
        organisms = new Organism[width][height];
        present = new CopyOnWriteArrayList<>();
        doodlePop = new int[30000];
        antPop = new int[30000];
        antMutation = 4000;
        doodleMutation = 1000;
        timeStep = 0;
        generateMap();
    }

//    void initialize()
//    {
//        setTitle("Game Tutorial");
//        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        setResizable(false);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        insets = getInsets();
//        setSize(insets.left + WINDOW_WIDTH + insets.right,
//                insets.top + WINDOW_HEIGHT + insets.bottom);
//
//        backBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
//    }

    private void generateMap()
    {
        int random;
        DoodleBug tempD;
        Ant tempA;
        for (int i = 0; i < width; i ++)
        {
            for (int j = 0; j < height; j++)
            {
                random = (int) (Math.random() * 1000);

                if (random < 125)
                {
                    tempD = new DoodleBug(i,j, new Color(0,45,210), 20);
                    organisms[i][j] = tempD;
                    addToPresent(tempD);
                    doodlePop[timeStep] ++;
                }
                else if (random < 300)
                {
                    tempA = new Ant(i,j, new Color(139,69,19), 2000);
                    organisms[i][j] = tempA;
                    addToPresent(tempA);
                    antPop[timeStep] ++;
                }
            }
        }
    }

//    public void main()
//    {
//        //initialize();
//        //generateMap();
//        boolean running = true;
//
//        while (running)
//        {
//            long time = System.currentTimeMillis();
//            update();
//            draw(getGraphics());
////            timeStep ++;
//            time = (1000 / FPS) - (System.currentTimeMillis() - time);
//            try
//            {
//                Thread.sleep(time);
//            }
//            catch(Exception e){}
//        }
//
////        printStats();
//
//    }

    public void update(double delta)
    {
//        if (delta > 1)
//        {
            timeStep ++;
            for (Organism o: present)
            {
                if (o.isAlive())
                {
                    o.update(this);
                    o.popPlus(this);
                }
                else
                {
                    present.remove(o);
                }
            }
        //}

    }



    public boolean isTileOccupied(Location loc)
    {
        return (organisms[loc.getX()][loc.getY()] != null);
    }

    public boolean isTileOccupied(int x, int y)
    {
        return (organisms[x][y] != null);
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void timeStepPlus()
    {
        timeStep += 1;
    }

    public void doodlePopPlus()
    {
        doodlePop[timeStep] += 1;
    }

    public void antPopPlus()
    {
        antPop[timeStep] += 1;
    }

    public void doodlePopMinus()
    {
        doodlePop[timeStep] -= 1;
    }

    public void antPopMinus()
    {
        antPop[timeStep] -= 1;
    }


//    public void draw(Graphics g)
//    {
//        Graphics bbg = backBuffer.getGraphics();
//        bbg.setColor(Color.BLACK);
//        bbg.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
//        DoodleBug temp = new DoodleBug();
//
//        for (Organism[] a: organisms)
//        {
//            for (Organism o: a)
//            {
//                if (o != null && o.getClass() != temp.getClass())
//                {
//                    draw( o, bbg);
//                }
//            }
//        }
//
//        g.drawImage(backBuffer, insets.left, insets.top, this);
//    }
//
//    public void draw(Organism o,Graphics bbg)
//    {
//            bbg.setColor(o.getColor());
//            bbg.fillRect(o.getLoc().getX() * (WINDOW_WIDTH / width), o.getLoc().getY() * (WINDOW_HEIGHT / height), WINDOW_WIDTH / width - 2, WINDOW_HEIGHT / height - 2);
//    }

    public Organism[][] getOrganisms()
    {
        return organisms;
    }

    public void addOrganism(Organism o)
    {
        organisms[o.getLoc().getX()][o.getLoc().getY()] = o;
    }

    public void addToPresent(Organism o)
    {
        present.add(o);
    }

    public CopyOnWriteArrayList<Organism> getPresent()
    {
        return present;
    }

    public int[] getDoodlePop()
    {
        return doodlePop;
    }

    public int[] getAntPop()
    {
        return antPop;
    }

    public int getTimeStep()
    {
        return timeStep;
    }

    public void reset()
    {
        organisms = new Organism[width][height];
        present = new CopyOnWriteArrayList<>();
        doodlePop = new int[30000];
        antPop = new int[30000];
        timeStep = 0;
        generateMap();
    }

    public void setDoodleMutation(int doodleMutation)
    {
        this.doodleMutation = doodleMutation;
    }

    public void setAntMutation(int antMutation)
    {
        this.antMutation = antMutation;
    }
}
