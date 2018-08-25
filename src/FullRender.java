import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FullRender extends BasicGameState
{
    private World map;
    private MenuItem[] buttons;
    private DoodleBug bug;
    private Ant ant;

    public FullRender(World map)
    {
        super();
        this.map = map;
    }

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException
    {
        bug = new DoodleBug();
        ant = new Ant();
        buttons = new MenuItem[4];
        buttons[0] = new MenuItem(container, null, new Rectangle(630,100,30,20), Color.red, Color.red.darker(), Color.red, "up", 0);
        buttons[1] = new MenuItem(container, null, new Rectangle(630,200,30,20), Color.red, Color.red.darker(), Color.red, "down", 0);
        buttons[2] = new MenuItem(container, null, new Rectangle(700,100,30,20), Color.blue, Color.blue.darker(), Color.blue, "up", 0);
        buttons[3] = new MenuItem(container, null, new Rectangle(700,200,30,20), Color.blue, Color.blue.darker(), Color.blue, "down", 0);
    }

    public void render(GameContainer container,StateBasedGame sbg, Graphics graphics)
    {
        Ant temp = new Ant();
        int dp, ap;
        for (int i = 0; i < map.getOrganisms().length; i ++)
        {
            for (int j = 0; j < map.getOrganisms()[i].length; j ++)
            {
                if (map.getOrganisms()[i][j] != null)
                {
                    graphics.setColor(map.getOrganisms()[i][j].getColor());
                    if (map.getOrganisms()[i][j].getClass() == temp.getClass())
                    {
                        graphics.fillRect(i * 6,j * 6,4,4);
                    }
                    else
                    {
                        graphics.drawRect(i * 6,j * 6,4,4);
                    }
                }
            }
        }
        int current;

        if (map.getTimeStep() - 450 < 0)
        {
            current = map.getTimeStep();
        }
        else {
            current = 450;
        }

        for (int i = 0; i < current; i ++)
        {
            graphics.setColor(Color.blue);
            graphics.drawLine(1200 - i,600 - map.getDoodlePop()[map.getTimeStep() - i] / 15 , 1200 - i + 2, 600 - map.getDoodlePop()[map.getTimeStep() - i + 2] / 15);
            graphics.setColor(Color.white);
            graphics.drawLine(1200 - i,600 - map.getAntPop()[map.getTimeStep() - i] / 15 , 1200 - i + 2, 600 - map.getAntPop()[map.getTimeStep() - i + 2] / 15);
        }

        graphics.setColor(Color.white);

        graphics.drawLine(750, 0, 750, container.getHeight());
        graphics.drawLine(600,20,750,20);
        graphics.drawLine(675, 20, 675, 300);
        graphics.drawLine(600,300,750,300);

        graphics.drawString("Mutation Rates", 610, 1);
        graphics.drawString("Ant", 601,21);
        graphics.drawString("" + ant.getMutationRate(),601, 40);
        graphics.drawString("DB", 676,21);
        graphics.drawString("" + bug.getMutationRate(), 676, 40);
        graphics.drawString("" + map.getTimeStep(), 550,1);

        for (MenuItem mi: buttons)
        {
            mi.render(container, graphics);
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame sbg, int delta)
    {
        map.update(delta);
        if (gameContainer.getInput().isKeyPressed(Input.KEY_0))
        {
            sbg.enterState(0);
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

        for (MenuItem mi: buttons)
        {
            if (mi.isMouseOver() && gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && mi.getX() == 700)
            {
                if (mi.getName().equals("up"))
                {
                    bug.setMutationRate(bug.getMutationRate() + 15);
                }
                else if (bug.getMutationRate() - 15 > 16)
                {
                    bug.setMutationRate(bug.getMutationRate() - 15);
                }
            }
            else if (mi.isMouseOver() && gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
            {
                if (mi.getName().equals("up"))
                {
                    ant.setMutationRate(ant.getMutationRate() + 15);
                }
                else if (ant.getMutationRate() - 15 > 16)
                {
                    ant.setMutationRate(ant.getMutationRate() - 15);
                }

            }
        }

    }

    public int getID()
    {
        return 1;
    }
}
