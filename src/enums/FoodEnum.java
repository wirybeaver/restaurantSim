package enums;

public enum FoodEnum {
    HAMBUGER(0, "Hambuger", 5),
    FRIES(1, "Fries", 3),
    COKE(2, "Coke", 2),
    ICECREAM(3, "Icecream", 1)
    ;
    private int id;
    private String name;
    private int makingTime;

    FoodEnum(int id, String name, int makingTime) {
        this.id = id;
        this.name = name;
        this.makingTime = makingTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMakingTime() {
        return makingTime;
    }

    public static int capacity(){
        return values().length;
    }

    public static FoodEnum indexOf(int id){
        for(FoodEnum x : values()){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }
}
