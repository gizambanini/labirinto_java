public class Matriz
{
	private int qtdeLinhas;
    private int qtdeColunas;
    private int qualLinha;
    private int qualColuna;
    private char[][] labirinto;
    
    public Matriz (char[][] labAux, int qtdLinha, int qtdColuna) throws Exception
    {
		if( qtdLinha < 0 || qtdColuna < 0 || labAux == null) // vê se os dados passados estão certos;
		   throw new Exception ("[ERROR] Dados invalido para a Matriz.");
		   
        this.labirinto = labAux;
        this.qtdeLinhas = qtdLinha;
        this.qtdeColunas = qtdColuna;
    }
	public char getInfoEmBaixo(CoordenadaXY essaCoord) throws Exception
	{
		// Pega a informação ('#'/'E'/'S'/' ') da posição de baixo;
		try
        {
            qualLinha = essaCoord.getLinha();
            qualColuna = essaCoord.getColuna();
        }
        catch (Exception erro)
        {
            System.out.println(" [ERROR] Erro no método getInfoEmBaixo()");
        }
        return labirinto[qualLinha + 1][qualColuna];
	}
	public char getInfoEmCima (CoordenadaXY essaCoord) throws Exception
	{
		// Pega a informação ('#'/'E'/'S'/' ') da posição de cima;
		try
        {
            qualLinha = essaCoord.getLinha();
            qualColuna = essaCoord.getColuna();
        }
        catch (Exception erro)
        {
            System.out.println(" [ERROR] Erro no método getInfoEmBaixo()");
        }
        return labirinto[qualLinha - 1][qualColuna];
	}
	public char getInfoNaDireita(CoordenadaXY essaCoord) throws Exception
	{
		// Pega a informação ('#'/'E'/'S'/' ') da posição da direita;
		try
        {
            qualLinha = essaCoord.getLinha();
            qualColuna = essaCoord.getColuna();
        }
        catch (Exception erro)
        {
            System.out.println(" [ERROR] Erro no método getInfoEmBaixo()");
        }
        return labirinto[qualLinha][qualColuna + 1];
	}
	public char getInfoNaEsquerda(CoordenadaXY essaCoord) throws Exception
	{
		// Pega a informação ('#'/'E'/'S'/' ') da posição da esquerda;
		try
        {
            qualLinha = essaCoord.getLinha();
            qualColuna = essaCoord.getColuna();
        }
        catch (Exception erro)
        {
            System.out.println(" [ERROR] Erro no método getInfoEmBaixo()");
        }
        return labirinto[qualLinha][qualColuna - 1];
	}
	public char getInfoNaPos(CoordenadaXY essaCoord) throws Exception
	{
		// Pega a informação ('#'/'E'/'S'/' ') da posição ;
		try
        {
            qualLinha = essaCoord.getLinha();
            qualColuna = essaCoord.getColuna();
        }
        catch (Exception erro)
        {
            System.out.println(" [ERROR] Erro no método getInfoNaPos()");
        }
        return labirinto[qualLinha][qualColuna];
	}
	public void marcarPos(int estaLinha, int estaColuna) throws Exception
	{
		// Marca o '*' na posição passada;
		if(labirinto[estaLinha][estaColuna] == 'E' || labirinto[estaLinha][estaColuna] == '#')
            throw new Exception ("[ERROR] Impossivel marcar  na posição: (" + estaLinha + " , " + estaColuna + ")");
        else
            labirinto[estaLinha][estaColuna] = '*';
	}
	public void desmarcarPos(int estaLinha, int estaColuna) throws Exception
	{
		// Tira o '*' na posição passada;
		if(labirinto[estaLinha][estaColuna] != '*')
            throw new Exception ("[ERROR] Impossível marcar  na posição: (" + estaLinha + " , " + estaColuna + ")");
        else
            labirinto[estaLinha][estaColuna] = ' ';
	}
	public boolean equals(Object obj)
	{ 
		if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;
            
        Matriz matriz = (Matriz)obj;
        
         if(this.qtdeLinhas != matriz.qtdeLinhas)
			return false;
		 if( this.qtdeColunas != matriz.qtdeColunas)
			return false;
		 if(this.qualLinha != matriz.qualLinha)
			return false;
		 if(this.qualColuna != matriz.qualColuna)
			return false;
		 if(this.labirinto != matriz.labirinto)
			return false;
			
		return true;
        
	}
	public int hashCode()
	{
		int ret = 6;
		try
        {
            ret = ret * 7 + new Integer(this.qtdeLinhas).hashCode();
            ret = ret * 7 + new Integer(this.qtdeColunas).hashCode();
            ret = ret * 7 + new Integer(this.qualColuna).hashCode();
            ret = ret * 7 + new Integer(this.qualLinha).hashCode();
            ret = ret * 7 + new Matriz(this.labirinto, qtdeLinhas, qtdeColunas).hashCode(); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (ret<0) ret =- ret;

        return ret;
	}

	public char[][] getLabirinto() 
	{ 
		return labirinto; 
	}

	public void desenharLabirinto()
	{
		System.out.println();
		 for( int l = 0; l < qtdeLinhas; l++)
		 {
			int c = 0;
			while(c  < qtdeColunas )
			{
				System.out.print(labirinto[l][c]);
				c++;
			}
			System.out.println();
		}
		System.out.println();
	 }


	// Construtor de cópia
	public Matriz (Matriz modelo) throws Exception
	{
		if(modelo == null)
            throw new Exception("Modelo ausente");
            
        this.qtdeColunas = modelo.qtdeColunas;
        this.qtdeLinhas = modelo.qtdeLinhas;
        this.qualColuna = modelo.qualColuna;
        this.qualLinha = modelo.qualLinha;
        this.labirinto = modelo.labirinto;
   }

	public Object clone ()
    { 
		Matriz ret = null;

        try
        {
            ret = new Matriz(this);
        }
        catch(Exception erro)
        {}

        return ret;
	}
	
}

