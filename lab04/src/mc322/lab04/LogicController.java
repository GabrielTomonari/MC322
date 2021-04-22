// Classe LogicController:
// 	validateCommand(): valida o movimento
// 	generateMove(): faz o calculo do movimento, retorna um array de 3 posições onde a primeira é o P, a segunda e a terceira são os -
// 	executeMove(): atualiza o estado usando as funções do StateManager

import java.lang.Math;

public class LogicController {
    private StateManager state;
    private char firLet;
    private char firNum;
    private char secLet;
    private char secNum;
    private char midNum;
    private char midLet;

    LogicController(StateManager state) {
        this.state = state;
    }

    public void setCommand(String command) {
        this.firLet = command.charAt(0);
        this.firNum = command.charAt(1);
        this.secLet = command.charAt(3);
        this.secNum = command.charAt(4);
        this.midNum = this.firNum > this.secNum ? (char) (this.secNum + 1) : (char) (this.firNum + 1);
        this.midLet = this.firLet > this.secLet ? (char) (this.secLet + 1) : (char) (this.firLet + 1);
    }

    public boolean validateCommand() {

        if (isEqual()) {
            return false;
        } else if (isOutOfRange()) {
            return false;
        } else if (isDiagonal()) {
            return false;
        } else if (targeIsPiece()) {
            return false;
        } else if (isInInvalidSquare()) {
            return false;
        } else if (middleIsDash()) {
            return false;
        } else if (originIsDash()) {
            return false;
        } else if (!isValidJump()) {
            return false;
        }

        return true;
    }

    private boolean isValidJump() {
        int numDif = Math.abs(this.firNum - this.secNum);
        int letDif = Math.abs(this.firLet - this.secLet);
        if ((numDif == 0 && letDif == 2) || (numDif == 2 && letDif == 0)) {
            return true;
        }

        return false;
    }

    private boolean originIsDash() {
        int originPos = Converter.parseStringPos(this.firLet, this.firNum);

        if (state.getPositionValue(originPos) == '-') {
            return true;
        } else {
            return false;
        }
    }

    private boolean middleIsDash() {
        int middlePos = 0;
        if (this.firLet == this.secLet) {
            middlePos = Converter.parseStringPos(this.firLet, this.midNum);
        }
        if (this.firNum == this.secNum) {
            middlePos = Converter.parseStringPos(this.midLet, this.firNum);
        }
        if (state.getPositionValue(middlePos) == '-') {
            return true;
        }
        return false;
    }

    private boolean isOutOfRange() {
        if ((this.firLet > 'g' || this.firNum > '7' || this.secLet > 'g' || this.secNum > '7')
                || (this.firLet < 'a' || this.firNum < '1' || this.secLet < 'a' || this.secNum < '1')) {
            return true;
        }
        return false;
    }

    private boolean isInInvalidSquare() {

        int targetPos = Converter.parseStringPos(this.secLet, this.secNum);
        char positionValue = state.getPositionValue(targetPos);
        if (positionValue == ' ') {
            return true;
        }
        int originPos = Converter.parseStringPos(this.secLet, this.secNum);
        positionValue = state.getPositionValue(originPos);
        if (positionValue == ' ') {
            return true;
        }
        return false;
    }

    private boolean targeIsPiece() {
        int targetPos = Converter.parseStringPos(this.secLet, this.secNum);
        char positionValue = state.getPositionValue(targetPos);
        if (positionValue == 'P') {
            return true;
        }
        return false;
    }

    public boolean isEqual() {
        if (firLet == secLet && firNum == secNum) {
            return true;
        }
        return false;
    }

    public boolean isDiagonal() {
        if (this.firLet != this.secLet && this.firNum != this.secNum) {
            return true;
        }
        return false;
    }

    public int[] generateMove() {
        int[] coordenadas = new int[3];

        if (this.firLet == this.secLet) {

            coordenadas[0] = Converter.parseStringPos(this.firLet, this.firNum);
            coordenadas[1] = Converter.parseStringPos(this.firLet, this.midNum);
            coordenadas[2] = Converter.parseStringPos(this.secLet, this.secNum);
        } else if (this.firNum == this.secNum) {

            coordenadas[0] = Converter.parseStringPos(this.firLet, this.firNum);
            coordenadas[1] = Converter.parseStringPos(this.midLet, this.firNum);
            coordenadas[2] = Converter.parseStringPos(this.secLet, this.secNum);
        }

        return coordenadas;
    }

    public void executeMove(int[] moves) {
        this.state.generateNewState();
        this.state.turnSpace(moves[0]);
        this.state.turnSpace(moves[1]);
        this.state.turnPiece(moves[2]);
    }
}
