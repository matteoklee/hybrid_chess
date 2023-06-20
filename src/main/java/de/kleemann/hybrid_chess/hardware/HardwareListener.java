package de.kleemann.hybrid_chess.hardware;

/**
 * Implementiere dieses Interface um die Klasse als HardwareListener im HardwareController registrieren zu können.
 * Der Listener wird immer dann benachrichtigt, wenn der Confirm-Move Knopf auf dem Brett gedrückt wurde.
 * @author Fabian Siemens
 */
public interface HardwareListener {
	/**
	 * Diese Funktion wird aufgerufen, sobald der Confirm-Move Knopf auf dem Brett gedrückt wurde.
	 * @param controller - Instanz des HardwareControllers für einfachen Zugriff auf weitere Funktionen
	 */
	public void onConfirmButtonPressed(HardwareController controller);
}
