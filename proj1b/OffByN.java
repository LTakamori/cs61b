public class OffByN implements  CharacterComparator{
    private int N;

    public OffByN(int offset){
        N = offset;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (((x-y) == N) || ((y-x) == N))
            return true;
        else
            return false;
    }
}