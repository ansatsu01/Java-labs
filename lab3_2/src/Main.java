import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new ArgumentAbsenceException("No arguments entered!");
            }
        } catch (ArgumentAbsenceException e) {
            System.out.println(e.getMessage());
        }
        StringBuilder result = new StringBuilder();
        System.out.println("You entered: " + Arrays.toString(args));
        for (int i = 0; i < args.length; i++) {
            result.append(changeStr(args[i]));
            result.append(" ");
        }
        System.out.println("The result: " + result);
    }
    public static StringBuilder changeStr(String s){  /// 125.25
        StringBuilder build = new StringBuilder(s);
        for(int i = 0; i < build.length()-2; i++){
                if(build.charAt(i) == '.' && isNumber(build.charAt(i + 1)) && isNumber(build.charAt(i + 2))){
                    i += 2;
                    while(i + 1 < build.length() && isNumber(build.charAt(i + 1)) ){
                        build.deleteCharAt(i + 1);
                    }
                }
        }
        return build;
    }

    public static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }
    private static class ArgumentAbsenceException extends Exception{
        public ArgumentAbsenceException(String message){
            super(message);
        }
    }
}
