import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;

import java.util.concurrent.ThreadLocalRandom;

public class DoodleBug extends Organism
{
    private static final Color[] colors = {new Color(0,191,255), new Color(0,0,205), new Color(70,130,180), new Color(123,104,238),
            new Color(65,105,225), new Color(95,158,160), new Color(0,255,255),
            new Color(102,205,170), new Color(0,128,128)};

    private int stepsNotEaten;
    private int hunger;
    private static int mutationRate;

    public DoodleBug()
    {
        super();
        stepsNotEaten = 0;
        hunger = 1;
        setStepsAliveNeeded(10);
    }

    public DoodleBug(int x, int y, Color color, int mutRate)
    {
        super(x,y,color);//new Color(ThreadLocalRandom.current().nextInt(100, 255), 0,0));
        stepsNotEaten = 0;
        hunger = 4;
        setStepsAliveNeeded(8);
        mutationRate = mutRate;
    }

    public DoodleBug(int x, int y, int[] moves, double[] dmoves, Color color)
    {
        super(x,y,moves, dmoves, color);
        stepsNotEaten = 0;


            setStepsAliveNeeded(moves.length - 1);
            hunger = ((moves.length / 3) + 1);

//            setStepsAliveNeeded(13);
//            hunger = (12);

    }

    public DoodleBug(Location loc,int[] moves, double[] dmoves, Color color)
    {
        super(loc, moves, dmoves, color);
        stepsNotEaten = 0;

            setStepsAliveNeeded(moves.length - 1);
            hunger = ((moves.length / 3) + 1);

//            setStepsAliveNeeded(13);
//            hunger = (12);

    }

    public void update(World map)
    {
            if (stepsNotEaten > hunger || Math.random() * 1000 < 30)
            {
                die(map);
            }
            else
            {
                eat(map);
                setStepsAlive(getStepsAlive() + 1);
            }

    }

    public void breed(World map)
    {
        DoodleBug db;
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
                    db = new DoodleBug(loc,mut, moves, colors[(int)(Math.random() * colors.length)]);
                }
                else
                {
                    db = new DoodleBug(loc,this.getMoves(), getdMovesTest(),this.getColor());
                }
                map.addToPresent(db);
                map.addOrganism(db);

            }
            setStepsAlive(0);
        }
    }

    public void eat(World map)
    {
        Location location;
        location =findAdjacentAnt(map);
        if (location != null)
        {
            map.getOrganisms()[location.getX()][location.getY()].setAlive(false);
            map.getOrganisms()[getLoc().getX()][getLoc().getY()] = null;
            getLoc().setAll(location.getX(), location.getY());
            map.addOrganism(this);
            stepsNotEaten = 0;
            breed(map);
        }
        else
        {
            move(map);
            breed(map);
            stepsNotEaten ++;
        }
    }

    public Location findAdjacentAnt(World map)
    {
//        Location[] locs = new Location[getMoves().length];
//        int counter = 0;
//
//        for (int i = 0; i < getMoves().length; i ++)
//        {
//            if (checkForAnt(map,MOVEMENT[getMoves()[i]]) != null)
//            {
//                locs[counter] = checkForAnt(map,MOVEMENT[getMoves()[i]]);
//                counter ++;
//            }
//        }
//
//        return locs[(int)(Math.random() * counter)];
        return checkForAnt(map,MOVEMENT[getMoves()[(int)(Math.random() * getMoves().length)]]);
    }

    public Location checkForAnt(World map, Location location)
    {
        Ant temp;
        location = new Location(location.getX() + getLoc().getX(), location.getY() + getLoc().getY());

        if (location.getX() >= 0 && location.getX() < map.getWidth() && location.getY() >= 0 && location.getY() < map.getHeight()  && map.getOrganisms()[location.getX()][location.getY()] != null)
        {
            if (map.getOrganisms()[location.getX()][location.getY()].getClass() != this.getClass())
            {
                return location;
            }
        }
        return null;
    }

    public void die(World map)
    {
        setAlive(false);
        map.getOrganisms()[this.getLoc().getX()][this.getLoc().getY()] = null;
    }

    public void popPlus(World map)
    {
       map.doodlePopPlus();
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
