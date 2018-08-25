import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState
{
    private MenuItem[] items;

    public Menu()
    {
        super();
        this.items = new MenuItem[3];
    }

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException
    {
        items[0] = new MenuItem(container, null, new Rectangle(0,0,600,200), new Color(0,200,0),new Color(0,150,0), new Color(0, 100, 0), "Preset 1", 1);
        items[1] = new MenuItem(container, null, new Rectangle(0,200,600,200), new Color(200,0,0), new Color(150,0,0), new Color(100,0,0), "Preset 2", 1);
        items[2] = new MenuItem(container, null, new Rectangle(0,400,600,200), new Color(0,0,200),new Color(0,0,150), new Color(0,0,100), "Custom", 1);
    }

    public void render(GameContainer container,StateBasedGame sbg, Graphics graphics)
    {
        for (MenuItem mi: items)
        {
            mi.render(container,graphics);
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame sbg, int delta)
    {
        for (MenuItem mi: items)
        {
            if (mi.isMouseOver() && gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
            {
                sbg.enterState(mi.getState());
            }
        }
    }

    public int getID()
    {
        return 3;
    }
}


