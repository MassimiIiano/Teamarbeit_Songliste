
public class Minimumsuche {
	
	public static void minimumsuche(int[] werte) {
		// der Algorithmus
		for (int i = 0; i < werte.length; i++) {
			int min = werte[i];
			// Position des kleinsten Wertes ab i
			int swapPos = 0;

			// sucht den kleinste Wert ab i
			for (int j = i + 1; j < werte.length; j++) {
				if (werte[j] < min) {
					min = werte[j];
					swapPos = j;
				}
			}

			// swap
			if (werte[i] != min) {
				int swap = werte[i];
				werte[i] = min;
				werte[swapPos] = swap;
			}
		}
	}
}
