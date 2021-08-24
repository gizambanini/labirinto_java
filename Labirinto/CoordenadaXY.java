import java.lang.reflect.*;
public class CoordenadaXY {
	
	int linhas, colunas;
	
	public CoordenadaXY (int x, int y)
	{
		this.linhas = x;
		this.colunas = y;
	}
	public CoordenadaXY ()
	{ }
	public int getLinha()
	{ 
		return linhas;
	}
	
	public int getColuna() 
	{
		return colunas;
	}
	
	public String toString()
	{ 
		return "(" + String.valueOf(linhas) + "," + String.valueOf(colunas) + ")";
	}
	
	public int hashCode()
	{ 
		int ret = 6;
		try
        {
            ret = ret * 7 + new Integer(this.linhas).hashCode();
            ret = ret * 7 + new Integer(this.colunas).hashCode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (ret<0) ret =- ret;

        return ret;

	}
	
	public boolean equals( Object obj)
	{ 
		if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        CoordenadaXY coord = (CoordenadaXY) obj;

       if(this.linhas != coord.linhas || this.colunas != coord.colunas)
            return false;

        return true;
	}
}

