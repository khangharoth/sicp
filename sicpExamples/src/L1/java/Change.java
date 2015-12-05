package L1.java;


public class Change {

    public static void main(String[] args) {
        Change change=new Change();
        System.out.println(change.countChange(100));
    }
    private int countChange(int amount){
        return cc(amount ,5);
    }
    private int cc (int amount , int kindOfCoins){
        if(amount==0){
            return 1;
        }
        else if ((amount < 0)|| (kindOfCoins ==0)){
            return 0;
        }
        return (cc(amount,(kindOfCoins -1))+cc(amount -(firstDenom(kindOfCoins)),kindOfCoins));
    }
    private int firstDenom(int kindOfCoins) {
        switch (kindOfCoins) {
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 25;
            case 5:
                return 50;

            default:
                return 0;
        }


    }
}
