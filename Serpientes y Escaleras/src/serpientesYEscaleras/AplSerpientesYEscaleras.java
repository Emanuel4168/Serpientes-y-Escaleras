package serpientesYEscaleras;

public class AplSerpientesYEscaleras {

	private static class Casilla{
		public int noCasilla;
		public char tipoCasilla;
		public int posiciones;
		
		public Casilla(int nc, char tc, int p) {
			noCasilla = nc;
			tipoCasilla = tc;
			posiciones = p;
		}
	}
	
	public AplSerpientesYEscaleras() {
		boolean avanza = true, finJuego = false;
		ListaDBL<Casilla> tablero = new ListaDBL();
		int nJugadores = Rutinas.nextInt(2,10), dado, ganador = 0;
		NodoDBL<Casilla> []  jugadores = new NodoDBL [nJugadores];
		NodoDBL<Casilla> jugador;
		
		llenarTablero(tablero);
		imbrimirTablero(tablero);
		//for(byte i = 0; i<jugadores.length; jugadores[i]=tablero.getFrente(), i++);
		
		/*while(!finJuego) {
			for(byte i =0; i < jugadores.length; i++) {
				dado = Rutinas.nextInt(2,12);
				System.out.println("dado:\t"+dado);
				//jugador = jugadores[i];
				if(jugadores[i] == null) {
					System.out.println("XXXXXXXXXXXXXXX");
					jugadores[i] = tablero.getFrente();
					dado--;
				}

				for(byte j = 0; j < dado; j++) {
					if(jugadores[i].getSig() == null)
						avanza = false;
					jugadores[i] = avanza? jugadores[i].getSig():jugadores[i].getAnt();
				}
				System.out.println("jugador "+(i+1)+"\tCasilla: "+jugadores[i].Info.noCasilla+"\n");
				
				if(jugadores[i].Info.tipoCasilla == 'E') {
					System.out.println("\033[32mEscaleras!!");
					while(jugadores[i].Info.posiciones != 1)
						jugadores[i] = jugadores[i].getSig();
					System.out.println("nueva posicion jugador "+(i+1)+"\tCasilla: "+jugadores[i].Info.noCasilla+"\n");
				}
				
				if(jugadores[i].Info.tipoCasilla == 'E') {
					System.out.println("\033[31mSerpientes!!");
					while(jugadores[i].Info.posiciones != 1)
						jugadores[i] = jugadores[i].getAnt();
					System.out.println("nueva posicion jugador "+(i+1)+"\tCasilla: "+jugadores[i].Info.noCasilla+"\n");
				}

				
				if(jugadores[i].Info.noCasilla == 100) {
					ganador = i+1;
					finJuego = true;
					break;
				}
				
				avanza = true;
			}
		}*/
		
		//System.out.println("El ganador es el jugador número "+ ganador);

	}
	
	public static void main(String[] args) {
		new AplSerpientesYEscaleras();
	}
	
	private void llenarTablero(ListaDBL<Casilla> tablero) {
		for(int i = 1; i <= 100; i++) 
			tablero.InsertaFin(new Casilla(i,'N',0));
		
		for(byte i =0; i<1; i++)
			if(!crearEscalera(tablero))
				i-=1;
		
		for(byte i =0; i<1; i++)
			if(!crearSerpiente(tablero))
				i-=1;
	}
	
	private boolean crearEscalera(ListaDBL<Casilla> tablero){
		NodoDBL<Casilla> inicio = tablero.getFrente();
		NodoDBL<Casilla> fin;
		int casillaInicio = Rutinas.nextInt(15, 70);
		int avance = Rutinas.nextInt(5,20);
		for(byte i =0; i < casillaInicio; inicio=inicio.getSig(), i++);
		if(inicio.Info.tipoCasilla != 'N')
			return false;
		fin = inicio.getSig();
		
		for(byte i =0; i < avance;  i++) {
			if(fin.Info.posiciones != 0)
				return false;
			fin.Info.posiciones = avance - i;
			fin = (i != avance-1)? fin.getSig():fin;
		}
		
		if(fin.Info.tipoCasilla != 'N')
			return false;
		
		inicio.Info.tipoCasilla = 'E';
		fin.Info.tipoCasilla = 'T';
		return true;
	}

	private boolean crearSerpiente(ListaDBL<Casilla> tablero){
		NodoDBL<Casilla> inicio = tablero.getFrente();
		NodoDBL<Casilla> fin;
		int casillaInicio = Rutinas.nextInt(30, 95);
		int avance = Rutinas.nextInt(5,20);
		for(byte i =0; i < casillaInicio; inicio=inicio.getSig(), i++);
		if(inicio.Info.tipoCasilla != 'N')
			return false;
		fin = inicio.getSig();
		
		for(byte i =0; i < avance; i++) {
			if(fin.Info.posiciones != 0)
				return false;
			fin.Info.posiciones = avance - i;
			fin = (i != avance-1)? fin.getAnt():fin;
		}
		
		if(fin.Info.tipoCasilla != 'N')
			return false;
		
		inicio.Info.tipoCasilla = 'S';
		fin.Info.tipoCasilla = 'T';
		return true;
	}	
	
	private void imbrimirTablero(ListaDBL<Casilla> l) {
		NodoDBL<Casilla> it = l.getFrente();
		while(it != null) {
			System.out.println(it.Info.noCasilla+"\t"+it.Info.tipoCasilla+"\t"+it.Info.posiciones);
			it = it.getSig();
		}
	}
}
