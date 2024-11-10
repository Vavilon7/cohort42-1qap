public class AddToCard {
    public static void main(String[] args) {
        String countStrElm ="(3)";
        String cellStr = countStrElm.replace( "(", "").replace(")","");
        System.out.println(cellStr);
        int countStar = Integer.parseInt(cellStr);
        System.out.println(cellStr+1);
    }
}
