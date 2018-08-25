import org.newdawn.slick.*;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.state.StateBasedGame;

public class Test extends StateBasedGame
{
    public World map;
    private static int height;
    private static int width;
    public int renderType;

    public static void main(String[] args)
    {
        AppGameContainer agc;
        try
        {
            agc = new AppGameContainer(new Test());
            agc.setDisplayMode(width,height,false);
            agc.setVSync(true);
            agc.setShowFPS(true);
            agc.start();
            agc.getGraphics().setLineWidth(1);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Test()
    {
        super("Predator Prey");
        this.width = 1200;
        this.height = 600;
        this.map = new World();
        this.renderType = 1;
    }

    public void initStatesList(GameContainer gc)
    {
        addState(new Menu());
        addState(new DBRender(this.map));
        addState(new FullRender(this.map));
        addState(new AntRender(this.map));
    }

}
