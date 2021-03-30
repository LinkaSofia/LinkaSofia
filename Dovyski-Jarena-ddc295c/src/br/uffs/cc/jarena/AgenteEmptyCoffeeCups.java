
/**
 * Um exemplo de agente que anda aleatoriamente na arena. Esse agente pode ser usado como base
 * para a criação de um agente mais esperto. Para mais informações sobre métodos que podem
 * ser utilizados, veja a classe Agente.java.
 * 
 * Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

package br.uffs.cc.jarena;

public class AgenteEmptyCoffeeCups extends Agente {
	int contador;
    int time = 20;
    int tempo = 0;
    boolean ganhandoEnergia;
    int recebeX = getX();
    int recebeY = getY();
	int direcaoCogumelo = getDirecao();
    int contaVitoria = 0;

	public AgenteEmptyCoffeeCups(Integer x, Integer y, Integer energia) {
		super(400, 500, energia);
		setDirecao(geraDirecaoAleatoria());
		contador = 0;
	}
	//Gera uma direção a cada x tempo 
	public void DirecaoContraria() {

        if ((contador % time) == 0) {
            if (getX() > recebeX) {
                podeMoverPara(ESQUERDA);
                
            } else {
                podeMoverPara(DIREITA);
            }
        }

        if ((contador % time) == 1) {
            if (getY() > recebeY) {
                podeMoverPara(BAIXO);
            } else {
                podeMoverPara(CIMA);
            }
        }
    } 
	
	public void pensa() {
		contador++;
		//Move para uma direção quando não está no cogumelo
		if(!podeMoverPara(getDirecao()) || (isParado() && !ganhandoEnergia)) {
			// Como não conseguimos nos mover, vamos escolher uma direção
			// nova.
			setDirecao(geraDirecaoAleatoria());
		}
		if ((contador % time) == 0) {
            setDirecao(geraDirecaoAleatoria());
        }
		
		if(podeDividir() && getEnergia() >= 800) {
			divide();
		}
	}
	
	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
		enviaMensagem("Recebendo energia");
        ganhandoEnergia = true;
        para();
		this.direcaoCogumelo = getDirecao();
        if(podeDividir() && getEnergia() >= 800) {
                divide();
                System.out.println("Tenho mais um irmao");
        }
	}
	
	public void tomouDano(int energiaRestanteInimigo) {
        if (getEnergia() < 10) {
            morre();
        }
        enviaMensagem("Tomando dano porra");
	}
	
	public void ganhouCombate() {
		enviaMensagem("Ganhamoooos!!");
	}
	
	public void recebeuMensagem(String msg) {
		if(msg.equals("Recebendo energia")){
			setDirecao(direcaoCogumelo);
		}
		if(msg.equals("Tomando dano porra")){
			if (getEnergia() < 400) {		
				setDirecao(geraDirecaoAleatoria());
			}
		}
		if(msg.equals("Ganhamoooos!!")){
			this.contaVitoria++;
		}
		System.out.println("Quantidade de vitórias: " + contaVitoria);
	}
	
	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "Fernando".
		return "AgenteEmptyCoffeeCups";
	}
}
