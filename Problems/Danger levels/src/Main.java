enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int denger;

    DangerLevel(int denger){
        this.denger=denger;
    }

    int getLevel() {
        return denger;
    }

}
