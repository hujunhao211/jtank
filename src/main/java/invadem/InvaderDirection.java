package invadem;

public enum InvaderDirection{
    moveLeft{public InvaderDirection change(){return moveDown1;}},
    moveRight{public InvaderDirection change(){return moveDown2;}},
    moveDown1{public InvaderDirection change(){return moveRight;}},
    moveDown2{public InvaderDirection change(){return moveLeft;}};

    InvaderDirection() {

    }

    public abstract InvaderDirection change();
}