/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garaauto;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class GaraAuto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s;
		
        Tracciato trac = new Tracciato(1000, 3);
        
        trac.addAuto(new Auto("Scuderia 1", new Pilota("Pilota 1"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 2", new Pilota("Pilota 2"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 3", new Pilota("Pilota 3"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 4", new Pilota("Pilota 4"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 5", new Pilota("Pilota 5"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 6", new Pilota("Pilota 6"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 7", new Pilota("Pilota 7"), 100, 150, trac));
        trac.addAuto(new Auto("Scuderia 8", new Pilota("Pilota 8"), 100, 150, trac));
        
		System.out.println("Vuoi truccare la gara? (y/N)");
		s = scanner.nextLine();
		if(s.equals("y")){
			System.out.println("Inserirre nome scuderia da far vincere");
			s = scanner.nextLine();
			if(trac.truccaGara(s)){
				System.out.println("Gara truccata con successo!");
			}else{
				System.out.println("Scuderia non trovata");
			}
		}
		
        trac.iniziaGara();
    }
    
}
