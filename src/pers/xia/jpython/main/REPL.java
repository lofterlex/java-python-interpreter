package pers.xia.jpython.main;

import pers.xia.jpython.ast.Module;
import pers.xia.jpython.grammar.GramInit;
import pers.xia.jpython.newinterpreter.Interpreter;
import pers.xia.jpython.object.Py;
import pers.xia.jpython.parser.Ast;
import pers.xia.jpython.parser.Node;
import pers.xia.jpython.parser.ParseToken;
import pers.xia.jpython.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class REPL {
    private static boolean pyReadMode =false;

    public static void main(String[] args) {
        driverLoop();
    }

    private static void driverLoop() {
        while (true) {
            System.out.print(">>> ");
            String input = readInput();
            if (input == null) {
                break;
            }
            if (input.equals("exit")) {
                break;
            }
            if (input.equals("PYREAD")){
                pyReadMode = true;
                pyReadLoop();
                continue;
            }
            try {
                evalLine(input);
            } catch (Exception e) {
                // Handle exceptions here
                e.printStackTrace();
            }
        }
    }

    private static String readInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void evalLine(String line) {
        // Implement your evaluation logic here
        // Replace this with the actual evaluation code
        PyReadMod mod = new PyReadMod(line);
        StringBuilder st = new StringBuilder();
        while (!mod.isEnd()){
            st.append(mod.format()).append("\n");
            System.out.print("...");
            line = readInput();
            mod = new PyReadMod(line);
        }
        String formatStr = mod.format();
        st.append(formatStr).append("\n");
        byte[] bytes = st.toString().getBytes();
        Node node = ParseToken.parseBytes(bytes, GramInit.grammar, 1);
        Ast ast = new Ast();
        // get the modType
        Module module = (Module) ast.fromNode(node);
//        Interpreter interpreter = new Interpreter(module.getBody());
        Interpreter interpreter = new Interpreter(module.getBody());
        interpreter.runProgram();
    }
    private static void pyReadLoop() {
        while (true) {
            System.out.print(">>>(Py-read) ");
            String input = readInput();
            if (input == null) {
                break;
            }
            if (input.equals("exit")) {
                pyReadMode = false;
                break;
            }
            try {
                PyReadMod mod = new PyReadMod(input);
                int num = mod.getIndentationLevel();
                String result = mod.format();
                System.out.println("(" + num + " " + result + ")");
            } catch (Exception e) {
                // Handle exceptions here
                e.printStackTrace();
            }
        }
    }
    private static class PyReadMod {
        static Map<Character,Character> mp = new HashMap<>();
        static {
            mp.put('{','}');mp.put('(',')');mp.put('[',']');
            mp.put(':','\n');
        }
        private String line;
        static Stack<Character> st = new Stack<>();
        public PyReadMod(String line) {
            this.line = line;
        }
        private String format(){
            StringBuilder sb = new StringBuilder();
            while (line.length()>0){
                Character c = peekChar();
                if(isNumber(c)&&sb.length()==0){ // number case
                    String appendStr = getFormatNum(line);
                    sb.append(appendStr);
                    break;
                }else if(isComment(c)){         //comment case
                    String appendStr = ignoreComment(line);
                    sb.append(appendStr);
                }else if(isString(c)){          //string case
                    String appendStr = getFormatString(line);
                    sb.append(appendStr);
                }else if(isFloat(c)){           //float case
                    String appendStr = getFormatDouble(line);
                    sb.append(appendStr);
                }else {
                    sb.append(readChar());
                }
            }
            return sb.toString();
        }

        private char peekChar(){
            return line.charAt(0);
        }
        private char readChar(){
            char c = line.charAt(0);
            line = line.substring(1);
            return c;
        }
        private String ignoreComment(String line){
            // function to be implemented
            return line;
        }
        private int getIndentationLevel(){
            // function to be implemented;
            int res = 0;
            while (peekChar()==' '){
                readChar();
                res++;
            }
            return res;
        }
        private String getFormatString(String line){
            char[] chs = line.toCharArray();
            // function to be implemented;
            return new String("");
        }
        private String getFormatDouble(String line){
            // function to be implemented;
            return new String("");
        }
        private String getFormatNum(String line){
            return "print(" + line + ")";
        }

        private boolean isNumber(Character c){
            return c>='0'&& c<='9';
        }
        private boolean isComment(Character c){
            return c == '#';
        }
        private boolean isString(Character c){
            return c.equals('\"');
        }
        private boolean isFloat(Character c){
            return c=='.';
        }
        private boolean isEnd(){
            char[] chars = line.toCharArray();
            while (chars.length==0&&!st.isEmpty()){
                if(st.peek()==':')st.pop();
                else break;
            }
            for (char aChar : chars) {
                if(mp.containsKey(aChar)){
                    st.push(aChar);
                }else {
                    if(st.isEmpty()) continue;
                    if(mp.get(st.peek()) != aChar) continue;
                    st.pop();
                }
            }
            return st.isEmpty();
        }
    }

}
