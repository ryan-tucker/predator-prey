import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;

import java.util.concurrent.ThreadLocalRandom;

public class Ant extends Organism
{
    private static final Color[] colors = {new Color(0,191,255), new Color(0,0,205), new Color(70,130,180), new Color(123,104,238),
                                     new Color(65,105,225), new Color(95,158,160), new Color(0,255,255),
                                     new Color(102,205,170), new Color(0,128,128)};
    private static int mutationRate;

    public Ant()
    {
        super();
    }

    public Ant(int xPos, int yPos,Color color, int mutRate)
    {
        super(xPos, yPos,color);//new Color(0, ThreadLocalRandom.current().nextInt(100, 255), 0));
        this.setStepsAliveNeeded(4);
        mutationRate = mutRate;
    }

    public Ant(int xPos, int yPos, int[] moves, double[] dmoves, Color color)
    {
        super(xPos, yPos, moves, dmoves, color);
       // this.setStepsAliveNeeded(2);
        setStepsAliveNeeded(10);
    }

    public Ant(Location loc, int[] moves, double[] dmoves,Color color)
    {
        super(loc, moves, dmoves, color);
       // this.setStepsAliveNeeded(2);
        setStepsAliveNeeded(10);
    }

    public void update(World map)
    {
//        if (getStepsAlive() > getMoves().length / 8)
//        {
//            die(map);
//        } else
//        {
            move(map);
            breed(map);
            setStepsAlive(getStepsAlive() + 1);
//        }
    }

    public void breed(World map)
    {
        Ant ant;
        if (getStepsAlive() > getStepsAliveNeeded())
        {
            int[] temp = findFreeTiles(map);
            Location loc = getRandomMove(temp);
            if (loc != null)
            {
                int[] mut;
                double[] moves;
                int random = ThreadLocalRandom.current().nextInt(0, mutationRate);
                mut = mutate(getMoves(), random);
                moves = mutate(getdMovesTest(), random);

                if (mut != null)
                {
                    ant = new Ant(loc,mut,moves,this.getColor());//new Color(ThreadLocalRandom.current().nextInt(0, 50), ThreadLocalRandom.current().nextInt(100, 200), 0));
                }
                else
                {
                    ant = new Ant(loc,this.getMoves(), this.getdMovesTest(), this.getColor());
                }
                map.addToPresent(ant);
                map.addOrganism(ant);
                setStepsAlive(0);
            }
        }
    }

    public void die(World map)
    {
        if (getStepsAlive() > 8)
        {
            setAlive(false);
            map.getOrganisms()[this.getLoc().getX()][this.getLoc().getY()] = null;
        }
    }

    public void popPlus(World map)
    {
        map.antPopPlus();
    }

    public void setMutationRate(int mutRate)
    {
        mutationRate = mutRate;
    }

    public int getMutationRate()
    {
        return mutationRate;
    }
}
