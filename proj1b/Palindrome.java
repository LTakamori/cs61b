import java.util.LinkedList;

public class Palindrome{
    /** Given a String, wordToDeque should return a Deque
        where the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> char_s = new LinkedListDeque<>();
        int length = word.length();
        for (int i = 0; i < length; i++){
            char_s.addLast(word.charAt(i));
        }
        return char_s;
    }

    /** test if the string is palindrome */
    public boolean isPalindrome(String word){
        if (word == null)
            return false;
        int length = word.length();
        for (int i = 0; i < length/2; i++){
            if (word.charAt(i) != word.charAt(length - 1 - i))
                return false;
        }
        return true;
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null)
            return false;
        int length = word.length();
        for (int i = 0; i < length/2; i++){
            if (!cc.equalChars(word.charAt(i),word.charAt(length - 1 - i)))
                return false;
        }
        return true;
    }
}



