import java.io.*;
public class TratadorArquivosLabirintos {
	
	BufferedReader leitorTxt = null;
	int qtdLinhas, qtdColunas;
	private char labirinto[][];
	
	public TratadorArquivosLabirintos () 
	{	}


	public void lerArquivo(String nomeArquivo)  throws IOException 
	{


		int qtdLinhasNoArquivo =0;
		int qtdColunasNoArquivo;

		try
		{
			 leitorTxt = new BufferedReader(new FileReader("Labirintos\\/" + nomeArquivo));
			 qtdLinhas = Integer.parseInt(leitorTxt.readLine());
			 qtdColunas =  Integer.parseInt(leitorTxt.readLine());
			 labirinto = new char [qtdLinhas][qtdColunas];
			 String str;



			 while ((str = leitorTxt.readLine()) != null) 
			 {
                labirinto[qtdLinhasNoArquivo] = str.toCharArray();
                qtdLinhasNoArquivo++;
                qtdColunasNoArquivo = str.length();

                if(qtdColunasNoArquivo != qtdColunas) 
                {
					// Se em alguma linha tiver mais colunas do que o esperado
					// Retorna uma mensagem de erro
					System.out.println("[ERROR] Quantidade de colunas incorreta!");
					System.exit(0);
				}

             }
			leitorTxt.close();

			if(qtdLinhasNoArquivo != qtdLinhas)
			{
				// Se tiver mais linhas do que o esperado 
				// Retorna uma mensagem de erro
				System.out.println("[ERROR] Quantidade de linhas incorreta!");
				System.exit(0);
			}  
		}
		catch (FileNotFoundException e)
        {
            System.err.println("[ERROR] FileNotFound: " + e.getMessage());
            System.exit(0);
        }
        catch (IOException e)
        {
            System.err.println("[ERROR] IOException: " + e.getMessage());
            System.exit(0);
        }
	}
	public int getTamanhoLabirinto()
    {
        int tamanhoLab = 0;
        tamanhoLab = qtdLinhas * qtdColunas;

        return tamanhoLab;
    }
     public char[][] getLabirinto() 
     { 
		 return labirinto; 
	 }
     public int getQtdLinhas() 
     { 
		 return qtdLinhas;
	 }
     public int getQtdColunas() 
     { 
		 return qtdColunas;
	 }
	 public CoordenadaXY acharEntradaLabirinto()
	 {
	 	CoordenadaXY coordEntrada = null;
	 	boolean achou = false;
	 	for(int l = 0; l < qtdLinhas; l++)
	 	{
			if(labirinto[l][0] == 'E') // ve primeira coluna
			{
				coordEntrada = new CoordenadaXY(l, 0);
				achou = true;
				break;
			}
			if(labirinto[l][qtdColunas - 1]  == 'E') // ve ultima coluna
			{
				coordEntrada = new CoordenadaXY(l, qtdColunas - 1);
				achou = true;
				break;
			}
		}
		if(achou == false)
		{
			for (int c = 0; c < qtdColunas; c++)
			{
				if(labirinto[0][c]  == 'E') // analisa linha 0 - primeira -  inteira
				{
					coordEntrada = new CoordenadaXY(0, c);
					achou = true;
					break;
				}
				if(labirinto[qtdLinhas - 1][c] == 'E') // analisa ultima linha  inteira
				{
					coordEntrada = new CoordenadaXY(qtdLinhas - 1, c);
					achou = true;
					break;
				}
			}
		}
		
	 	return coordEntrada;
	 }
	 public void verificarLabirinto()
	 {
		// Vê se tem 2 entradas, 2 saídas, paredes, se tem um caracter "estranho"
		byte qntsEntradas = 0;
        byte qntsSaidas = 0;
		boolean naoTemParede = false;


        try
        {
            for (int x = 0; x < qtdLinhas; x++)
            {
                for (int y = 0; y < qtdColunas; y++)
                {
                	if (labirinto[x][y] != 'E' && labirinto[x][y] != ' ' && labirinto[x][y] != 'S' && labirinto[x][y] != '#')
					{
						System.out.println("[ERROR] O labirinto  possui um caracter que o programa não reconhece!!");
						System.exit(0);
					}

                    if (labirinto[x][y] == 'E')
                        qntsEntradas++;

                    if (labirinto[x][y] == 'S')
                        qntsSaidas++;
                }
            }

            if (qntsEntradas != 1)
            {
                System.out.println("[ERROR] O labirinto nao possui a quantidade certa de entradas!");
                System.exit(0);
            }
            if (qntsSaidas != 1)
            {
                System.out.println("[ERROR] O labirinto nao possui a quantidade certa de saidas!");
                System.exit(0);
            }
           
            for(int l = 0; l < qtdLinhas; l++)
			{
				if(labirinto[l][0] == ' ')
				{				
					naoTemParede = true;
					break;
				}
				if(labirinto[l][qtdColunas - 1]  == ' ')
				{			
					naoTemParede = true;
					break;
				}
			}
			if(naoTemParede == false)
			{
				for (int c = 0; c < qtdColunas; c++)
				{

					if(labirinto[0][c]  == ' ')
					{					
						naoTemParede = true;
						break;
					}
					if(labirinto[qtdLinhas - 1][c] == ' ')
					{
						naoTemParede = true;
						break;
					}
				}
			}
			if(naoTemParede == true)
            {
                System.out.println("[ERROR] O labirinto nao possui paredes!");
                System.exit(0);
            }
        }
        catch(Exception ex)
        { System.out.println("[ERROR] Labirinto está errado!");}
	 }
	 public String toString()
	 {
		 return "";
	 }
	 public int hashCode()
    {
        int ret = 6;
        ret = ret * 7 + new Integer(qtdColunas).hashCode();
        ret = ret * 7 + new Integer(qtdLinhas).hashCode();

        for (int x = 0; x < labirinto.length; x++)
        {
            for (int y = 0; y < labirinto[x].length; y++)
            {
                ret = ret * 7 + new Character(labirinto[x][y]).hashCode();
            }
        }

        if (ret<0) ret =- ret;

        return ret;
    }
	 public boolean equals(Object obj)
	 {
		 if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        TratadorArquivosLabirintos tratador = (TratadorArquivosLabirintos) obj;
		if (this.qtdColunas != tratador.qtdColunas)
		   return false;
		   
		if (this.qtdLinhas != tratador.qtdLinhas)
		   return false;
		  
		for(int l = 0; l < this.qtdLinhas; l++)
		{
			for(int c = 0; c < this.qtdColunas; c++)
			{
				if(this.labirinto[l][c] != tratador.labirinto[l][c])
				   return false;
			}
		}
        return true;
	 }

	 // construtor de copia
     public TratadorArquivosLabirintos (TratadorArquivosLabirintos modelo) throws Exception
     {
        if(modelo == null)
            throw new Exception("[ERROR] Modelo não foi passado!");
     }

     public Object clone ()
     {
        TratadorArquivosLabirintos ret=null;

        try
        {
            ret = new TratadorArquivosLabirintos(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
}

