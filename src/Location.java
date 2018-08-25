public class Location
{
    private int x;
    private int y;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setAll(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Location other)
    {
        if (other == null)
        {
            return false;
        }
        else
        {
            return this.x == other.x && this.y == other.y;
        }
    }

    public void add(Location other)
    {
        this.x += other.x;
        this.y += other.y;

    }

    public Location check(Location other)
    {
        return new Location(this.x + other.x, this.y + other.y);
    }

}
