public class Programa{
	
	public static void main (String[] args) 
	{
		TratadorArquivosLabirintos tratadorLabirintos = new TratadorArquivosLabirintos();
		String opcao;
		String modo = "Progressivo";
		Pilha<CoordenadaXY> caminho;
		Pilha<Fila<CoordenadaXY>> possibilidades;
		Fila<CoordenadaXY> fila;
			
				try
				{
					System.out.println ();
					System.out.println ("************************************************");
					System.out.println ("Grupo: Giovana Zambanini (20178) e Isabela Clementino Ponciano (20138) ");
					System.out.println ();
					System.out.println ("************************************************");
					System.out.println ("Bem vindo(a)! ");
					System.out.println ("Digite o nome do arquivo texto para que o sistema ache o caminho ate a saida!");
					System.out.println ("OBS: Nao esqueca de escrever o '.txt' ao fim!");
					System.out.println ();
					System.out.print   (" > Nome do arquivo (.txt): ");
					opcao = (Teclado.getUmString()).toLowerCase(); // Solicia a digitação do nome do arquivo

					tratadorLabirintos.lerArquivo(opcao); // Lê o arquivo;
					tratadorLabirintos.verificarLabirinto(); // Vê se o labirinto está correto ( 1 entrada, 1 saída e com paredes);
				  
					//cria a matriz labirinto;
					Matriz labirinto = new Matriz(tratadorLabirintos.getLabirinto(), tratadorLabirintos.getQtdLinhas(), tratadorLabirintos.getQtdColunas());
					//Instancia o caminho;
					caminho = new Pilha<CoordenadaXY> (tratadorLabirintos.getTamanhoLabirinto());
					// Instancia a possibilidades;
					possibilidades = new Pilha<Fila<CoordenadaXY>> (tratadorLabirintos.getTamanhoLabirinto());
				  
					if(tratadorLabirintos.acharEntradaLabirinto() == null)
					{ 
						 //Se não achou a entrada dá um errro e acaba o programa;
						 System.out.println("[ERROR] Nao foi possivel encontrar a entrada!");
					}
				    else
				    { 
					   // Achou a ÚNICA entrada;
					   CoordenadaXY atual = new CoordenadaXY();  //
					   atual = tratadorLabirintos.acharEntradaLabirinto(); // atual recebe a coordenada da entrada;
					   boolean achouSaida = false;
					   fila = new Fila<CoordenadaXY>(3); 
					   while(achouSaida != true)
					   {
						   // Enquanto não tiver achado a saida;
						   if(modo == "Progressivo")
						   {
							   // Instanciar a fila			  
							   fila = new Fila<CoordenadaXY>(3); // instancia a fila com 3 coordenadas;
							  // Guarda os dados na fila
							  try
							  {
									 if(atual.getLinha() < tratadorLabirintos.getQtdLinhas())
									 {
										 // Vê pos em baixo se não estiver na última linha;
										 if(labirinto.getInfoEmBaixo(atual) != 'E' && labirinto.getInfoEmBaixo(atual) != '#' && labirinto.getInfoEmBaixo(atual) != '*')
										 {
											 fila.guardeUmItem(new CoordenadaXY(atual.getLinha() + 1, atual.getColuna()));
										 }
									 }
									 if(atual.getLinha() > 0)
									 {
										 // Vê pos em cima se não estiver na 1º linha;
										 if(labirinto.getInfoEmCima(atual)!= 'E' && labirinto.getInfoEmCima(atual)!= '#' && labirinto.getInfoEmCima(atual)!= '*')
										 {
											 fila.guardeUmItem(new CoordenadaXY(atual.getLinha() - 1, atual.getColuna()));
										 }
									 }
									 if(atual.getColuna() < tratadorLabirintos.getQtdColunas())
									 {
										 // Vê pos na direita se não estiver na  última coluna;
										 if(labirinto.getInfoNaDireita(atual) != '#' && labirinto.getInfoNaDireita(atual) != 'E' && labirinto.getInfoNaDireita(atual) != '*')
										 {
											 fila.guardeUmItem(new CoordenadaXY(atual.getLinha(), atual.getColuna() + 1));
										 }
									 }
									 if( atual.getColuna() > 0)
									 {
										 // Vê pos na esquerda se não estiver na primeira coluna;
										 if(labirinto.getInfoNaEsquerda(atual) != '#' && labirinto.getInfoNaEsquerda(atual) != 'E' && labirinto.getInfoNaEsquerda(atual) != '*')
										 {
											 fila.guardeUmItem( new CoordenadaXY(atual.getLinha(), atual.getColuna() - 1));
										 }
									 }
								
								}
								catch(Exception err){ System.out.println("[ERROR]1");}
							}
						 
							// Vê se a fila tá vazia
							if(fila.isVazia())
							{ 
								modo = "Regressivo"; // vai para o modo regressivo;
							}
							 else 
							 { 
								modo = "Progressivo"; //volta para o modo progressivo;
								atual = fila.recupereUmItem(); // atual recebe uma coordenda de fila;
								fila.removaUmItem(); // tira essa coordanada da fila;
								if(labirinto.getInfoNaPos(atual) == 'S') // vê se achou a saida
								{
									 achouSaida = true;
									 
									 Pilha<CoordenadaXY> inverso = new Pilha<CoordenadaXY>(tratadorLabirintos.getTamanhoLabirinto());
									 System.out.print("Caminho: ");
									 while(!caminho.isVazia()) // enquanto caminho ainda não tiver terminado;
									 {
										 inverso.guardeUmItem(caminho.recupereUmItem()); // inverso recebe a coordenada de caminho;
										 caminho.removaUmItem(); // retira a coordenada de caminho;
									 }
									 while (!inverso.isVazia())
									 {
										 // enquanto não tiver terminado o inverso;
										 System.out.print(inverso.recupereUmItem() + " "); // printa os dados de inverso separados por um espaço;
										 inverso.removaUmItem(); // remove a coordenada de inverso;
									 }
									 System.out.println();
									 labirinto.desenharLabirinto(); //printa o labirinto com os '*'
									 System.exit(0);
								}
								
								labirinto.marcarPos(atual.getLinha(), atual.getColuna()); // marca a posição indicada com '*';
								caminho.guardeUmItem(atual); // Empilha atual em caminho
								possibilidades.guardeUmItem(fila); // Empilha fila em possibilidades
								
							}
							 
							if(modo == "Regressivo" && achouSaida != true)
							{								
								// Se tiver em modo regressivo ( fila está vazia) E ainda não achou a saída
								if(caminho.isVazia() && possibilidades.isVazia())
								{
									 System.out.println("[ERROR] Nao foi indentificado nenhum caminho da entrada para a saida!");
									 System.exit(0);
								}
								atual = caminho.recupereUmItem(); // atual recebe uma coordenada de caminho;
								caminho.removaUmItem();  // remove essa coordenada de caminho;
								labirinto.desmarcarPos(atual.getLinha(), atual.getColuna()); // retira o '*' da posição indicada;
								fila = possibilidades.recupereUmItem(); // fila recebe um item de possibilidade;
								possibilidades.removaUmItem(); // retira esse item de possibilidades;					
								
						   }
					}
				}
			}
			catch (Exception erro)
			{
				System.err.println (erro.getMessage());
			}
	 }

}
