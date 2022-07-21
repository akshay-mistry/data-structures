import java.awt.*;

public class Wall {


	private int[] x;
	private int[] y;
	private int r;
	private int g;
	private int b;
	private String type;
	private int dist;



	public Wall(int[] x, int[] y, int r, int g, int b, String type, int dist) {
		this.y = y;
		this.x = x;
		this.r = r;
		this.g = g;
		this.b = b;
		this.type = type;
		this.dist = dist;

	}

	public Polygon getPoly() {
		return new Polygon(x, y, y.length);
	}

	public String getType()
	{
		return type;
	}

	public GradientPaint getPaint()
	{
		int endR = r-dist;
		int endG = g-dist;
		int endB = b-dist;
		if(r < 0)
			r = 0;
		if(g < 0)
			g = 0;
		if(b < 0)
			b = 0;
		if(endR < 0)
			endR = 0;
		if(endG < 0)
			endG = 0;
		if(endB < 0)
			endB = 0;
		switch(type)
		{
			case "Right":
			case "Left":
				return new GradientPaint(x[0],y[1], new Color(r,g,b), x[1], y[1], new Color(endR, endG, endB));
		}

		if(y.length==4)
			return new GradientPaint(x[0],y[0], new Color(r,g,b), x[0], y[1], new Color(endR, endG, endB));

		return new GradientPaint(x[0],y[0], new Color(r,g,b), x[2], y[0], new Color(endR, endG, endB));
	}

}
