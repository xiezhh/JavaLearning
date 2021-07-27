/**
 * @ClassName:Order
 * @description:
 * @author:xiezhh
 * @create:2021-06-20 9:35
 * @Version 1.0
 */

public class Order {

        private Order(){};

        private static Order instavnce = new Order();

        public static Order getInstance(){
            return instavnce;
        }

}
