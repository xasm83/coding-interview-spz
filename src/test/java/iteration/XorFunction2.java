package iteration;

public class XorFunction2 implements Function2<Integer, Integer, Integer> {

    @Override
    public Integer apply(Integer integer1, Integer integer2) {
        return integer1 ^ integer2 << 15;
    }
}
