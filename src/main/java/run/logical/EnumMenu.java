package run.logical;

public enum EnumMenu {

    ONE(1,"Create flights"),
    TWO(2,"Show all flight"),
    THIRD(3,"Find flight deals"),
    FOUR(4,"Book flight"),
    FIVE(5,"Show booked flight"),
    SIX(6,"Exit");


    private final int menuInt;
    private final String menuString;

    EnumMenu(int menuInt, String menuString) {
        this.menuInt = menuInt;
        this.menuString = menuString;
    }

    public String getMenuString() {
        return menuString;
    }

    public int getMenuInt() {
        return menuInt;
    }
}
