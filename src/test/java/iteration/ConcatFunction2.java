package iteration;

public class ConcatFunction2 implements Function2<Integer, String, String> {

    @Override
    public String apply(Integer integer, String s) {
        return integer + s;
    }
}
