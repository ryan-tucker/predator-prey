import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class MenuItem extends MouseOverArea
{
    private String name;
    private int state;

    public MenuItem(GUIContext container, Image image, Shape shape, Color mouseOverColor, Color color, Color mouseDownColor, String name, int state)
    {
        super(container, image, shape);
        setMouseOverColor(mouseOverColor);
        setNormalColor(color);
        setMouseDownColor(mouseDownColor);
        this.name = name;
        this.state = state;
    }

    public void render(GUIContext gc, Graphics g)
    {
        super.render(gc, g);
        g.setColor(Color.white);
        if (name != null)
        {
            g.drawString(name, (getX() + (getWidth() / 2)) - (gc.getDefaultFont().getWidth(name) / 2), getY() + (getHeight() / 2));
        }
    }

    public String getName()
    {
        return name;
    }

    public int getState()
    {
        return state;
    }
}
