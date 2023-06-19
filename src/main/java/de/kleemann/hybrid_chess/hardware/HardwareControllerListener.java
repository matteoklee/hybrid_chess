package de.kleemann.hybrid_chess.hardware;

import de.fabiansiemens.hardwarecontroller.HardwareController;
import de.fabiansiemens.hardwarecontroller.HardwareListener;

import java.util.ArrayList;

public class HardwareControllerListener implements HardwareListener {

    private boolean[][] field;

    public HardwareControllerListener() {
        field = new boolean[8][8];

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                switch(i) {
                    case 0, 1, 6, 7 -> field[i][j] = true;
                    default -> field[i][j] = false;
                }
            }
        }
    }

    @Override
    public void onConfirmButtonPressed(HardwareController hardwareController) {
        boolean[][] updatedField = hardwareController.readField();
        ArrayList<Integer> differences = getDifferences(field, updatedField);

        if(differences == null) throw new IllegalArgumentException("Exception in onConfirmButtonPressed: Differences are null");

        // TODO: Update ChessGame

        field = updatedField;
    }

    /**
     * Liefert eine ArrayList mit Integern, welche Indizes der Positionen beinhaltet.
     * Liefert immer eine gerade Zahl an Elementen zurück. <br /> <br />
     *
     * 1. Index: Reihe <br />
     * 2. Index: Spalte
     *
     * @param matrix
     * @param matrix2
     * @return Indizes von Positionen, welche sich verändert haben.
     */
    private ArrayList<Integer> getDifferences(boolean[][] matrix, boolean[][] matrix2) {
        if(matrix.length != matrix2.length || matrix[0].length != matrix2[0].length) return null;
        
        ArrayList<Integer> differences = new ArrayList<>();
        
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] != matrix2[i][j]) {
                    differences.add(i);
                    differences.add(j);
                }
            }
        }
        return differences;
    }
}
