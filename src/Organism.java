import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Organism
{
    public static final Location[] MOVEMENT = new Location[]{new Location( 0,1), new Location(0, -1),
                                                             new Location( 1,0), new Location(-1, 0),
                                                             new Location( 1,1), new Location(1, -1),
                                                             new Location(-1,1), new Location(-1,-1)};
    private static final int MAX_MOVES = 10;
    private int stepsAlive;
    private boolean isAlive;
    private boolean inPurgatory;
    private int stepsAliveNeeded;
    private int[] moves;
    private double[] dMovesTest;
    private Color color;
    private Location loc;
    private int lastMove;

    public Organism()
    {
        stepsAlive = 0;
        isAlive = true;
        inPurgatory = false;
    }

    public Organism(int xPos, int yPos, Color color)
    {
        stepsAlive = 0;
        loc = new Location(xPos, yPos);
        isAlive = true;
        moves = new int[2];
        dMovesTest = new double[8];
        this.color = color;

//        for (int i = 0; i < 2; i ++)
//        {
//            dMovesTest[i + 1] = .3;
//        }
        int index;
        for (int i = 0; i < 8; i ++)
        {
           index = (int)(Math.random() * dMovesTest.length);
            dMovesTest[i] = (Math.random() * .5);
        }

        for (int i = 0; i < moves.length; i ++)
        {
            moves[i] = (int)(Math.random() * MOVEMENT.length);
        }
//
//        for (int i = 0; i < moves.length; i ++)
//        {
//            moves[i] = (int)(Math.random() * MOVEMENT.length);
//        }
//        for (int i = 0; i < 25; i++)
//        {
//            moves[i] = 0;
//        }
//
//
//        for (int i = 25; i < 50; i++)
//        {
//            moves[i] = 4;
//        }
//
//
//        for (int i = 50; i < 75; i ++)
//        {
//            moves[i] = 5;
//        }
//
//        for (int i = 75; i < moves.length; i ++)
//        {
//            moves[i] = (int)(Math.random() * MOVEMENT.length);
//        }

    }

    public Organism(int xPos, int yPos,int[] moves,double[] dmoves, Color color)
    {
        this.stepsAlive = 0;
        this.isAlive = true;
        this.loc = new Location(xPos, yPos);
        this.moves = moves;
        this.color = color;
        this.dMovesTest = dmoves;
    }

    public Organism(Location loc, int[] moves,double[] dmoves, Color color)
    {
        this.loc = loc;
        this.stepsAlive = 0;
        this.isAlive = true;
        this.moves = moves;
        this.dMovesTest = dmoves;
        this.color = color;
    }

    public void update(World map)
    {

    }

    public void move(World map)
    {
        int[] temp = findFreeTiles(map);
        Location location = getRandomMove(temp);
        if (location != null)
        {
            map.getOrganisms()[loc.getX()][loc.getY()] = null;
            this.loc = location;
            map.addOrganism(this);
        }
    }

    //Returns Location in form of (dx, dy)
    public int[] findFreeTiles(World map)
    {
        int[] freeTiles = new int[8];
        int counter = 0;

//        if (dMovesTest != null)
//        {
            for (int i = 0; i < dMovesTest.length; i ++)
            {
                if (dMovesTest[i] > 0)
                {
                    if (locationIsValid(i,map))
                    {
                        freeTiles[counter] = i;
                        counter ++;
                    }
                }
            }
      //  }
        int[] tiles2 = new int[counter];
        for (int i = 0; i < counter; i ++)
        {
            tiles2[i] = freeTiles[i];
        }

        return tiles2;
    }

    public int getStepsAlive()
    {
        return stepsAlive;
    }

    public void setStepsAlive(int stepsAlive)
    {
        this.stepsAlive = stepsAlive;
    }

    public int getStepsAliveNeeded()
    {
        return stepsAliveNeeded;
    }

    public void setStepsAliveNeeded(int stepsAliveNeeded)
    {
        this.stepsAliveNeeded = stepsAliveNeeded;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public Location getLoc()
    {
        return loc;
    }

    public boolean hasMove(Location loc)
    {
        return true;
    }

    //freeTiles contains indexes of locations(dx,dy) in MOVEMENT
    public Location getRandomMove(int[] freeTiles)
    {
        double sum = 0;
        Location[] moves = new Location[8];
        int counter = 0;

        for (int i = 0; i < freeTiles.length; i ++)
        {
            if (dMovesTest[freeTiles[i]] > Math.random())
            {
//                sum += dMovesTest[freeTiles[i]];
//                moves[counter] = new Location(this.getLoc().getX() + MOVEMENT[freeTiles[i]].getX(), this.getLoc().getY() + MOVEMENT[freeTiles[i]].getY());
//                counter ++;
                return new Location(this.getLoc().getX() + MOVEMENT[freeTiles[i]].getX(), this.getLoc().getY() + MOVEMENT[freeTiles[i]].getY());
            }
        }

//        if (counter == 0)
//        {
//            return null;
//        }
//        if (counter == 1)
//        {
//            return moves[counter - 1];
//        }
//        else
//        {
//            sum = (int)(Math.random() * sum);
//
//            for (int i = 0; i < counter - 1; i ++)
//            {
//                if (sum < dMovesTest[i])
//                {
//                    return new Location(this.getLoc().getX() + MOVEMENT[freeTiles[i]].getX(), this.getLoc().getY() + MOVEMENT[freeTiles[i]].getY());
//                }
//                else
//                {
//                    sum += dMovesTest[i];
//                }
//            }
//        }

//        if (freeTiles == null)
//        {
//            return null;
//        }
//        else
//        {
//
//            int index = (int)(Math.random() * freeTiles.length);
//            return new Location(this.getLoc().getX() + MOVEMENT[index].getX(), this.getLoc().getY() + MOVEMENT[index].getY());
//        }

        return null;
    }

    public int[] getMoves()
    {
        return moves;
    }

    public double[] mutate(double[] moves, int random)
    {
        double[] temp;
        temp = null;

        if (random > 8)
        {
            return null;
        }
        else if (random < 8)
        {
            temp = mutateMoves((int)(Math.random() * random), .2);
        }

        return temp;
    }

    public int[] mutate(int[] moves, int random)
    {
        int[] temp;
        temp = null;
        if (random > 8)
        {
            return null;
        }
        else if (random < 2)
        {
            temp = copyMovesPlusOne(moves);
            random = (int)(Math.random() * moves.length);
            temp[temp.length - 1] = moves[random];
        }
        else if (random < 3)
        {
            temp = copyMovesPlusOne(moves);
            random = (int)(Math.random() * MOVEMENT.length);
            temp[temp.length - 1] = random;
        }
        else if (random < 5)
        {
            temp = copyMovesNeg(moves);
        }
        else if (random < 7)
        {
            temp = copyMoves(moves);
            random = (int)(Math.random() * temp.length);
            temp[random] = (int)(Math.random() * MOVEMENT.length);
        }
        else if (random < 8)
        {
            temp = copyMoves(moves);
            random = (int)(Math.random() * temp.length);

            for (int i = random; i < temp.length - 1;i ++)
            {
                temp[i + 1] = temp[i];
            }

            temp[random] = (int)(Math.random() * MOVEMENT.length);
        }

        return temp;

    }

    public int[] copyMoves(int[] moves)
    {
        int[] temp;

        temp = new int[moves.length];

        for (int i = 0; i < moves.length; i ++)
        {
            temp[i] = moves[i];
        }

        return temp;
    }

    public int[] copyMovesPlusOne(int[] moves)
    {
        int[] temp;
        temp = new int[moves.length + 1];

        for (int i = 0; i < moves.length; i ++)
        {
            temp[i] = moves[i];
        }
        return temp;
    }

    public int[] copyMovesNeg(int[] moves)
    {
        int[] temp;
        int oIndex = (int)(Math.random() * moves.length    );
        int cIndex = (int)(Math.random() * moves.length - 1);

        if (cIndex == -1)
        {
            cIndex = 0;
        }

        if (moves.length -1 != 0)
        {
            temp = new int[moves.length - 1];

            for (int i = 0; i < temp.length; i ++)
            {
                if (i == cIndex)
                {
                    temp[i] = moves[oIndex];
                }
                else
                {
                    temp[i] = moves[i];
                }
            }
        }
        else
        {
            temp = new int[]{moves[cIndex]};
        }

        return temp;
    }

    public boolean isInPurgatory()
    {
        return inPurgatory;
    }

    public void setInPurgatory(boolean inPurgatory)
    {
        this.inPurgatory = inPurgatory;
    }

    public void setColor(Color c)
    {
        color = c;
    }

    public Color getColor()
    {
        return color;
    }

    public void popPlus(World map)
    {
    }

    public boolean locationIsValid(int index, World map)
    {
        int x,y,dx,dy;
        x = getLoc().getX();
        y = getLoc().getY();
        dx = MOVEMENT[index].getX();
        dy = MOVEMENT[index].getY();

        if (x + dx > -1 && x + dx < map.getWidth() && y + dy > -1 && y + dy < map.getHeight() && !map.isTileOccupied(x + dx, y + dy))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double[] getdMovesTest()
    {
        return dMovesTest;
    }

    public double[] mutateMoves(int index, double delta)
    {
        double[] temp = new double[8];

        for (int i = 0; i < temp.length; i ++)
        {
                temp[i] = getdMovesTest()[i];
        }
        temp[index] += delta;

        return temp;
    }
}
