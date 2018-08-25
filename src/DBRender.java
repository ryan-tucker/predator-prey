import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DBRender extends BasicGameState
{
    private World map;

    public DBRender(World map)
    {
        super();
        this.map = map;
    }

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException
    {

    }

    public void render(GameContainer container,StateBasedGame sbg, Graphics graphics)
    {
        int dp, ap;
        DoodleBug temp = new DoodleBug();
        Ant tempa = new Ant();
        for (int i = 0; i < map.getOrganisms().length; i ++)
        {
            for (int j = 0; j < map.getOrganisms()[i].length; j ++)
            {
                if (map.getOrganisms()[i][j] != null)
                {
                    if (map.getOrganisms()[i][j].getClass() == temp.getClass())
                    {
                        graphics.setColor(map.getOrganisms()[i][j].getColor());
                        graphics.fillRect(i * 6,j * 6,4,4);
                    }

//                    graphics.setColor(map.getOrganisms()[i][j].getColor());
//                    graphics.fillRect(i * 6,j * 6,4,4);
                }
            }
        }
        int current;

        if (map.getTimeStep() - 450 < 0)
        {
            current = map.getTimeStep();
        }
        else
            {
            current = 450;
        }

        for (int i = 0; i < current; i ++)
        {
            graphics.setColor(Color.red);
            graphics.drawLine(1200 - i,600 - map.getDoodlePop()[map.getTimeStep() - i] / 15 , 1200 - i + 2, 600 - map.getDoodlePop()[map.getTimeStep() - i + 2] / 15);
            graphics.setColor(Color.green);
            graphics.drawLine(1200 - i,600 - map.getAntPop()[map.getTimeStep() - i] / 15 , 1200 - i + 2, 600 - map.getAntPop()[map.getTimeStep() - i + 2] / 15);
        }

        graphics.setColor(Color.white);
        graphics.drawLine(750, 0, 750, container.getHeight());
        graphics.drawString("" + map.getTimeStep(), 550,1);
    }

    public void update(GameContainer gameContainer, StateBasedGame sbg, int delta)
    {
        map.update(delta);

        if (gameContainer.getInput().isKeyPressed(Input.KEY_1))
        {
            sbg.enterState(1);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_2))
        {
            sbg.enterState(2);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            sbg.enterState(3);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_R))
        {
            this.map.reset();
        }
    }

    public int getID()
    {
        return 0;
    }
}
